package com.sokolov.microservlet;

import com.sokolov.microservlet.annotation.RenderForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.Introspector;

/**
 * Helper class RequestFormUtil.
 * @author Sergei Sokolov
 * @version 1.0
 */
public class RequestFormUtil {

    /** Holds dispatch parameter name. */
    private static final java.lang.String FORM_BEAN_PARAMETER_NAME = "RequestFormBean";

    /**
     * Get request form.
     * @param request HttpServletRequest
     * @return a value of requestForm property
     */
    final public static RequestForm getRequestForm(HttpServletRequest request) {
        return (RequestForm) request.getAttribute(FORM_BEAN_PARAMETER_NAME);
    }

    /**
     * Set request form.
     * @param request HttpServletRequest
     * @param requestForm RequestForm
     * @param requestForm a new value of requestForm property
     */
    final public static void setRequestForm(HttpServletRequest request,
                                            RequestForm requestForm) {
        request.setAttribute(FORM_BEAN_PARAMETER_NAME, requestForm);
    }

    /**
     * Apply request values.
     *
     * @param form a form bean
     * @param request - an HttpServletRequest  object that contains the request the client has made of the servlet
     * @throws IllegalAccessException - if an error occurs
     */
    final public static void applyRequestValues(RequestForm form,
                                                HttpServletRequest request)
            throws IllegalAccessException {
        try {

        	if (form == null) {
        		throw new IllegalStateException("The RequestForm is null.");
        	}

            final PropertyDescriptor[] descriptors =
                    Introspector.getBeanInfo(form.getClass()).getPropertyDescriptors();

            for (final PropertyDescriptor descriptor : descriptors) {

                // get request parameter
                String parameterValue = request.getParameter(descriptor.getName());

                final Method writeMethod = descriptor.getWriteMethod();
                if (writeMethod != null) {

                    // transform string value to property type
                    Object value = null;
                    if (descriptor.getPropertyType() == String.class) {
                        value = parameterValue;
                    } else if (descriptor.getPropertyType() == Integer.class) {
                        if ((parameterValue != null) && (!"".equals(parameterValue))) {
                            try {
                                value = new Integer(parameterValue);
                            } catch (NumberFormatException nfe) {
                                nfe.printStackTrace();
                            }
                        }
                    } else if (descriptor.getPropertyType() == Long.class) {
                        if ((parameterValue != null) && (!"".equals(parameterValue))) {
                            try {
                                value = new Long(parameterValue);
                            } catch (NumberFormatException nfe) {
                                nfe.printStackTrace();
                            }
                        }
                    } else if (descriptor.getPropertyType() == Double.class) {
                        if ((parameterValue != null) && (!"".equals(parameterValue))) {
                            try {
                                value = new Double(parameterValue);
                            } catch (NumberFormatException nfe) {
                                nfe.printStackTrace();
                            }
                        }
                    } else if (descriptor.getPropertyType() == Float.class) {
                        if ((parameterValue != null) && (!"".equals(parameterValue))) {
                            try {
                                value = new Float(parameterValue);
                            } catch (NumberFormatException nfe) {
                                nfe.printStackTrace();
                            }
                        }
                    } else {
                        continue;
                    }

                    // excecute set method
                    try {
                        writeMethod.invoke(form, value);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates form, execute method and render JSP.
     *
     * @param renderFormAnnotation a discriptor of form bean
     * @param request - an HttpServletRequest  object that contains the request the client has made of the servlet
     * @throws ClassNotFoundException - if an error occurs
     * @throws NoSuchMethodException - if an error occurs
     * @throws java.lang.reflect.InvocationTargetException - if an error occurs
     * @throws IllegalAccessException - if an error occurs
     * @throws InstantiationException - if an error occurs
     */
    final public static RequestForm getFormBean(RenderForm renderFormAnnotation,
                                                HttpServletRequest request)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
                   IllegalAccessException, InstantiationException {
        HttpSession session = request.getSession(true);
        RequestForm form = null;
        if (renderFormAnnotation == null) {
        	throw new IllegalStateException("RenderFormAnnotation is not set.");
        }
        if (Scope.SESSION.equals(renderFormAnnotation.scope())) {
            form = (RequestForm) session.getAttribute(renderFormAnnotation.name());
        }
        if (form == null) {
            try {
                Class formClass = renderFormAnnotation.clazz();
                Constructor constructor = formClass.getConstructor();
                form = (RequestForm) constructor.newInstance();

                session.setAttribute(renderFormAnnotation.name(), form);
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
        }
        request.setAttribute(renderFormAnnotation.name(), form);

        executeLifeCycle(form, request);

        return form;
    }

    /**
     * Execute form bean life cycle.
     *
     * @param form a form bean
     * @param request - an HttpServletRequest  object that contains the request the client has made of the servlet
     * @throws IllegalAccessException - if an error occurs
     */
    final protected static void executeLifeCycle(RequestForm form,
                                                 HttpServletRequest request)
            throws IllegalAccessException {
        form.reset();
        RequestFormUtil.applyRequestValues(form, request);
        form.validate();
    }
}
