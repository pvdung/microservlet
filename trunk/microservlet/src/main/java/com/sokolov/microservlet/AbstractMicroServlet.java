package com.sokolov.microservlet;

import com.sokolov.microservlet.annotation.DispatchMethod;
import com.sokolov.microservlet.annotation.RenderForm;
import com.sokolov.microservlet.annotation.RenderJsp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Abstract MicroServlet.
 *
 * @author Sergei Sokolov
 * @version 1.0
 */
@SuppressWarnings("serial")
public abstract class AbstractMicroServlet  extends HttpServlet {

    /** Hols dispatch parameter name. */
    private static final java.lang.String DISPATCH_PARAMETER_NAME = "method";

    /**
     * Called by the server (via the service method) to allow a servlet to handle a GET request.
     *
     * @param request - an HttpServletRequest  object that contains the request the client has made of the servlet
     * @param response - an HttpServletResponse  object that contains the response the servlet sends to the client
     * @throws ServletException - if the request for the GET could not be handled
     * @throws IOException - if an input or output error is detected when the servlet handles the GET request
     */
    final protected void doGet(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {
        doDispatch(request, response);
    }

    /**
     * Called by the server (via the service method) to allow a servlet to handle a POST request.
     *
     * @param request - an HttpServletRequest  object that contains the request the client has made of the servlet
     * @param response - an HttpServletResponse  object that contains the response the servlet sends to the client
     * @throws ServletException - if the request for the POST could not be handled
     * @throws IOException - if an input or output error is detected when the servlet handles the POST request
     */
    final protected void doPost(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
        doDispatch(request, response);
    }

    /**
     * Get dispatch parameter value.
     *
     * @param request HttpServletRequest
     * @return String a value of dispatch parameter
     */
    protected String getDispachParameterValue(HttpServletRequest request) {
        return request.getParameter(DISPATCH_PARAMETER_NAME);
    }

    /**
     * Dispatch GET or POST request.
     *
     * @param request - an HttpServletRequest  object that contains the request the client has made of the servlet
     * @param response - an HttpServletResponse  object that contains the response the servlet sends to the client
     * @throws ServletException - if the request could not be handled
     * @throws IOException - if an input or output error is detected
     */
    final protected void doDispatch(HttpServletRequest request,
                                    HttpServletResponse response)
            throws ServletException, IOException {
        String parameterValue = getDispachParameterValue(request);
        doDispatch(request, response, parameterValue);
    }

    /**
     * Send message.
     *
     * @param response - an HttpServletResponse  object that contains the response the servlet sends to the client
     * @param body - a body of Http answer
     * @throws IOException - if an input or output error is detected
     */
    protected void send(HttpServletResponse response,
                        String body)
            throws IOException {
        // It's open question how to implement this method
        PrintWriter out = response.getWriter();
        out.println(body);
    }

    /**
     * Dispatch GET or POST request.
     *
     * @param request - an HttpServletRequest  object that contains the request the client has made of the servlet
     * @param response - an HttpServletResponse  object that contains the response the servlet sends to the client
     * @param parameterValue - dispatch parameter value
     * @throws ServletException - if the request could not be handled
     * @throws IOException - if an input or output error is detected
     */
    public void doDispatch(HttpServletRequest request,
                           HttpServletResponse response,
                           String parameterValue)
            throws ServletException, IOException {
        if ((parameterValue == null) || ("".equals(parameterValue))) {
            // execute default view
            try {
                Method method = this.getClass().getMethod("doView", HttpServletRequest.class, HttpServletResponse.class);
                DispatchMethod dispatchMethod = method.getAnnotation(DispatchMethod.class);
                if (dispatchMethod != null) {
                    executeMethod(method, request, response);
                } else {
                    send(response, "Default method 'doView' is not implemented.");
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else {
            parameterValue.trim();
        }

        // execute dispatch view
        Method[] methods = this.getClass().getMethods();
        for (Method method: methods) {
            DispatchMethod dispatchMethod = method.getAnnotation(DispatchMethod.class);
            if (dispatchMethod != null) {
                String annotationValue = dispatchMethod.value();
                if (annotationValue == null) {
                    annotationValue = "";
                } else {
                    annotationValue = annotationValue.trim();
                }

                if ("".equals(annotationValue)) {
                    // value property of DispatchMethod annotation is not defined
                    String methodName = method.getName();
                    if ((dispatchMethod.checkRegister() && (methodName.equals("do"+parameterValue)))
                        || ((!dispatchMethod.checkRegister()) && (methodName.equalsIgnoreCase("do"+parameterValue)))) {
                        executeMethod(method, request, response);
                    }
                } else {
                    // value property of DispatchMethod annotation is defined
                    if ((dispatchMethod.checkRegister() && (annotationValue.equals(parameterValue)))
                        || ((!dispatchMethod.checkRegister()) && (annotationValue.equalsIgnoreCase(parameterValue)))) {
                        executeMethod(method, request, response);
                    }
                }
            }
        }
    }

    /**
     * Build set of method parameters.
     *
     * @param request - an HttpServletRequest  object that contains the request the client has made of the servlet
     * @param response - an HttpServletResponse  object that contains the response the servlet sends to the client
     * @return array of parameters
     */
    protected Object[] getMethodParameters(HttpServletRequest request,
                                           HttpServletResponse response) {
        Object methodArguments[] = new Object[2];
        methodArguments[0] = request;
        methodArguments[1] = response;
        return methodArguments;
    }

    /**
     * Creates form, execute method and render JSP.
     *
     * @param method executed method
     * @param request - an HttpServletRequest  object that contains the request the client has made of the servlet
     * @param response - an HttpServletResponse  object that contains the response the servlet sends to the client
     * @throws ServletException - if the request could not be handled
     * @throws IOException - if an input or output error is detected
     */
    final protected void executeMethod(Method method,
                                       HttpServletRequest request,
                                       HttpServletResponse response)
            throws IOException, ServletException {
        // create form
        RenderForm renderFormAnnotation = method.getAnnotation(RenderForm.class);
        if (renderFormAnnotation != null) {
            try {
                RequestForm formBean = RequestFormUtil.getFormBean(renderFormAnnotation, request);
                RequestFormUtil.setRequestForm(request, formBean);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        // execute method
        try {
            Object methodArguments[] = getMethodParameters(request, response);
            method.invoke(this, methodArguments);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // render jsp response

        RenderJsp renderJspAnnotation = method.getAnnotation(RenderJsp.class);
        if ((renderJspAnnotation != null)
                && (renderJspAnnotation.page() != null)
                && (!"".equals(renderJspAnnotation.page()))) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(renderJspAnnotation.page());
            //dispatcher.include(request, response);
            dispatcher.forward(request, response);
        }
    }


    /**
     * Default executed method.
     *
     * @param request - an HttpServletRequest object that contains the request the client has made of the servlet
     * @param response - an HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException - if the request could not be handled
     * @throws IOException - if an input or output error is detected
     */
    protected abstract void doView(HttpServletRequest request,
                                   HttpServletResponse response)
            throws ServletException, IOException;
}

