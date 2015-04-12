![http://ajaxportal.googlecode.com/svn/trunk/images/english.gif](http://ajaxportal.googlecode.com/svn/trunk/images/english.gif) [Hot news: Our home site AJAXPORTAL.ORG is available!!!](http://ajaxportal.org/en/microservlet.html)

My dream was implemented. Now I can write easy Java Web application which has just one Java class. MicroServlet Java MVC framework supports JSP, form beans, value validation, dispatch rendering.


# MicroServlet Overview #

## What is MicroServlet? ##

MicroServlet is easiest Java MVC framework which is based on HttpServlet. MicroServlet supports JSP, form beans (supports <i>REQUEST</i> and <i>SESSION</i> scopes), value validation, action dispatching and JSP rendering.

<i>MicroServlet</i> framework can be used for implementation of Portlet API for <a href='http://code.google.com/p/ajaxportal/'>Ajax Portal</a>.

# MicroServlet Reqirements #

  1. Java 1.5 (supports Java annotations),
  1. Servlet API 2.4 or higher

# Introduction #

<b>MicroServlet</b> is MVC framework where <b>MicroServlet main class</b> is a controller, <b>request form bean</b> is a model and <b>render JSP</b> is a view.
<i>MicroServlet main class</i> is custom implementation of <i>HttpServlet</i> and has to be extended from <b>AbstractMicroServlet</b>.

Any implementation of MicroServlet main class has to have one method <b>doView</b> which is abstract in <i>AbstractMicroServlet</i>.
The method <i>doView</i> is one of dispatch methods and it's executed if other dispatch methods are not found or are not suitable.
The method (like other dispatch methods) has two parameters like the methods <i>doGet</i> and <i>doPost</i> of <i>HttpServlet</i>:
  1. <i>HttpServletRequest</i> request - HTTP request,
  1. <i>HttpServletResponse</i> response - HTTP response.

Any dispatch method has to be marked by <i>@DispatchMethod</i> annotation (the method <i>doView</i> is not an exception of this rule).
@DispatchMethod annotation has two properties:
  1. <i>value</i> - defines a value of dispatch method for invocation of marked method (the dispatch method can have any name except the names of other dispatch methods).
  1. <i>checkRegister</i> - defines a condition "register check of dispatch value is required or not".
If the properties are not defined in <i>@DispatchMethod</i> annotation the following dispatch method for an action/invoked method is used:
the name of the method is <b>doXXX</b>, where <i>XXX</i> is a value of dispatch parameter which first symbol of value is capitalized.


<i>MicroServlet</i> has dispatch parameter which defines invoked method of MicroServlet center class. By default the parameter name is <i>method</i>.

For each dispatch method can be defined <i>request form bean</i> by <i>@RenderForm</i> annotation and a render JSP by <i>@RenderJsp</i> annotation.

<b>MicroServlet</b> framework gives a possibility to build a small application based on two files only:
<i>MicroServlet main class</i> and <i>web.xml</i>. The following two listings demonstrates Hello world example for <b>MicroServlet</b> framework

**Listing A: Hello World example (**<i>MicroServlet main class</i>)**```
public class MicroServletExample extends AbstractMicroServlet {

    @DispatchMethod
    public void doView(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<b>Hello, World!</b>");
    }
}
```**

**Listing B: Hello World example (**<i>web.xml</i>)**```
<?xml version="1.0"?>
<!DOCTYPE web-app PUBLIC
  "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
  "http://java.sun.com/dtd/web-app_2_3.dtd">
<web-app>
    <servlet>
        <servlet-name>Demo MicroServlet</servlet-name>
        <servlet-class>com.sokolov.microservlet.example.MicroServletExample</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>Demo MicroServlet</servlet-name>
        <url-pattern>*.do</url-pattern>
    </servlet-mapping>
</web-app>
```**


The access to the application will be following:
```
http://<server_name>:<port>/<application_path>/<some_page>.do
```
or
```
http://<server_name>:<port>/<application_path>/<some_page>.do?method=view
```
As result you have to see <b>Hello, World!</b> in the browser window.<br />
Of course you will see the same result, even you put any value of the parameter <b>method</b>, because there're not other dispatch methods in <i>MicroServletExample</i> class except the method <i>doView</i>.

<i>MicroServlet main class</i> implements a framework MVC controller by parent <i>AbstractMicroServlet</i> and all application business logic by deispatch methods.
It is an advantage for a small application, but it's not suitable for a big application.
<b>MicroServlet</b> framework is very good for implementanion of news, blogs, user registaration, etc.

# MicroServlet lifecycle #

The five phases of the MicroServlet application lifecycle are as follows (very similar with JSF lifecycle):
  1. <b>Restore form bean</b> - If the scope of request form bean is SESSION, the bean get from the session, otherwse the bean is created as a new instance (the scope is REQUEST). The phase is executed if the annotation <i>@RenderForm</i> is defined for the dispatch method.
  1. <b>Apply request values</b> - The method <b>reset</b> of the request form bean is executed by a framework contrioller and then parameters of request put in the form bean by the method <b>applyRequestValues</b> of the <i>RequestFormUtil</i> class. The phase is executed if the annotation <i>@RenderForm</i> is defined for the dispatch method.
  1. <b>Process validations</b> - The method <b>validate</b> of the request form bean is executed by a framework contrioller. All validation errors can be stored in the list which is avalable by the method <b>getErrors</b> of the request form bean. The phase is executed if the annotation <i>@RenderForm</i> is defined for the dispatch method.
  1. <b>Invoke application</b> - The controller try to find dispatch method which is appropriate for the request and start it otherwise the controller starts the method <b>doView</b>.
  1. <b>Render response</b> - JSP defined by the annotation <i>@RenderJsp</i> is used for a view generation. The phase is executed if the annotation <i>@RenderJsp</i> is defined for the dispatch method.


# MicroServlet Example #


<b>MicroServlet</b> example has 3 files:
<ol>
<blockquote><li><b>MicroServletExample.java</b> - servlet supports 4 dispatch methods (doView, doEdit, myHelp and mySuperPage).</li>
<li><b>DemoFormExample.java</b> - form bean which has one parameter <i>param1</i> and two methods defined by RequestForm interface.</li>
<li><b>demo.jsp</b> - JSP for dispatch method <i>mySuperPage</i>.</li>
</ol></blockquote>


<i>MicroServletExample</i> servlet suports 4 dispatch methods:
  1. <b>doView</b> - method demonstrats:
  * a standard dispatch method detection ("do" + `<method_parameter_value>`, where first symbol of `<method_parameter_value>` is capitalized),
  * a request form bean creation (instance of com.sokolov.microservlet.example.DemoFormExample) in SESSION scope; the form bean will be accessible by DemoForm name on JSP,
  * render JSF response based on demo.jsp (path to JSF is set from application root).
  1. <b>doEdit</b> - method demonstrats only a standard dispatch method detection (see method <i>doView</i>).
  1. <b>myHelp</b> - method demonstrats only a custom dispatch method detection (the value of method parameter is set by value property of DispatchMethod annotation).
  1. <b>mySuperPage</b> - method demonstrats:
  * a custom dispatch method detection (see method <i>myHelp</i>),
  * a request form bean creation (see method <i>doView</i>).


**Listing #1: MicroServletExample.java**
```
public class MicroServletExample extends AbstractMicroServlet {

    protected DemoFormExample getRequestForm(HttpServletRequest request) {
        return (DemoFormExample) RequestFormUtil.getRequestForm(request);
    }

    @DispatchMethod
    @RenderForm (name = "DemoFormExample",
                 clazz = com.sokolov.microservlet.example.DemoFormExample.class,
                 scope = Scope.SESSION) // Scope.REQUEST by default
    @RenderJsp (page = "/page/demo.jsp")
    public void doView(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        DemoFormExample formExample = getRequestForm(request);
        formExample.setParam1("Test");
    }

    @DispatchMethod
    public void doEdit(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("EDIT: Hello, World");
    }

    @DispatchMethod (value = "help")
    public void myHelp(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("HELP: Hello, World");
    }

    @DispatchMethod (value = "super")
    @RenderForm (name = "DemoFormExample",
                 //scope = Scope.SESSION, // Scope.REQUEST by default
                 clazz = com.sokolov.microservlet.example.DemoFormExample.class)
    public void mySuperPage(HttpServletRequest request,
                            HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("HELP: Hello, World");

        DemoFormExample formExample = getRequestForm(request);
        out.println("param1 = " + formExample.getParam1());
    }
}
```

DemoFormExample declares <i>param1</i> property and three methods which are declared by RequestForm interface (see Struts ActionForm class):
  * <b>reset</b> - method clean value of the form bean (e.g. supports checkboxes in SESSION scope),
  * <b>validate</b> - method validates request parameters,
  * <b>getErrors</b> - method returns a list of validation errors (see ValidationError class).

**Listing #2a: DemoFormExample.java (first possible version)**
```
public class DemoFormExample implements RequestForm {

    private String param1;

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public void reset() {
        // empty implementation (use for SESSION scope)
    }

    public void validate() {
        // empty implementation
    }

    public List&lt;ValidationError&gt; getErrors() {
        // TODO: not implemented
        return null;
    }
}
```

or DemoFormExample extends default form implementation RequestFormImpl:

**Listing #2b: DemoFormExample.java (an alternative version)**
```
public class DemoFormExample extends <b>RequestFormImpl</b> {

    private String param1;

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }
}
```

The last listing shows JSP which is used on <b>Render Response</b> phase for the view generation.
There're two JSP tags:
  1. <b>jsp:useBean</b> defines request form bean which is got from the attribute <i>DemoForm</i> of HttpRequest.
  1. <b>jsp:getProperty</b> gets the value of the property <i>param1</i>.

**Listing #3: demo.jsp**
```
&lt;html&gt;
    &lt;head&gt;
        &lt;title&gt;Demo&lt;/title&gt;
    &lt;/head&gt;
    &lt;body&gt;
        &lt;jsp:useBean
            id="DemoForm"
            scope="request"
            class="com.sokolov.microservlet.example.DemoFormExample" /&gt;

        &lt;h1&gt;Demo&lt;/h1&gt;
        &lt;p&gt;
            param1 = &lt;jsp:getProperty name="DemoForm" property="param1" /&gt;
        &lt;/p&gt;
    &lt;/body&gt;
&lt;/html&gt;
```

Download the demo <a href='download/microservlet_example.war'>microservlet_example.war</a> (<a href='download/microservlet_example-sources.zip'>sources</a>).


# MicroServlet Implementation #


<b>MicroServlet</b> implementation is based on the following Java classes:
  1. <b>DispatchMethod.java</b> - annotation marks dispatch methods of the servlet implementation.
  1. <b>RenderForm.java</b> - annotation defines class, scope, name for request form bean.
  1. <b>RenderJsp.java</b> - annotation defines render JSP.
  1. <b>RequestForm.java</b> - interface defines reset, validate and getErrors methods (see Struts ActionForm class).
  1. <b>RequestFormImpl.java</b> - default implementation of the interface RequestForm.
  1. <b>ValidationError.java</b> - DTO class has two text properties: message and bundleKey.
  1. <b>Scope.java</b> - unum declares two scopes for request form bean: <i>REQUEST</i> and <i>SESSION</i>.
  1. <b>AbstractMicroServlet.java</b> - servlet implementaion supported method dispatching, form bean creation and JSP rendering.
  1. <b>RequestFormUtil.java</b> - service methods for form bean.

The annotation <i>DispatchMethod</i> marks dispatch methods in the center class of MicroServlet application. The methods will be invoked during <b>Invoke Application</b> phase.
For each dispatch method can be defined a request form and a render JSP.
If a form is not defines (no <i>RenderForm</i> annotation for the invoke method) the request parameter are got from the <i>HttpServletRequest</i>.
If a render JSP is not defines (no <i>RenderJsp</i> annotation for the invoke method) the view is generated by out stream writer of <i>HttpServletResponse</i>.

**Listing #1: DispatchMethod.java**
```
public @interface DispatchMethod {
    String value() default "";
    boolean checkRegister() default false;
}
```

The annotation <i>RenderForm</i> defines main parameters of a request form bean (class name, name of request attribute and scope where the request form bean will be saved).

**Listing #2: RenderForm.java**
```
public @interface RenderForm {
    String name() default "form";
    <b>Class clazz();</b> // one of the changes in release 1.1
    Scope scope() default Scope.REQUEST;
}
```

The annotation <i>RenderJsp</i> defines path to the path to JSP which will be used on <b>Render Response</b> phase.

**Listing #3: RenderJsp.java**
```
public @interface RenderJsp {
    String page();
}
```

The request form has private declaration of all required properties and public set/get method for access to the private properties.
I think you see some similar ideas with Struts action form...

**Listing #4: RequestForm.java**
```
public interface RequestForm {
    void reset();
    void validate();
    List&lt;ValidationError&gt; getErrors();
}
```

<i>RequestFormImpl</i> is default implementation of the interface <i>RequestForm</i>.
It's very comfortable to use this class in a simple pilot project.
<i>RequestFormImpl</i> defines empty implementations of the methods <i>reset</i> (start before <b>Apply Request Values</b> phase)
and <i>validate</i> (start on <b>Process Validations</b> phase).
Moreover, it defines a list of <i>ValidationError</i>, which is returned by the method <i>getErrors</i>.
The list can be used in <i>validate</i> method of Request form bean, in dispatch method and on render JSP.

**Listing #5: RequestFormImpl.java**
```
public class RequestFormImpl implements RequestForm {
    private List&lt;ValidationError&gt; errors = new ArrayList&lt;ValidationError&gt;();

    public void reset() {
        // empty implementation
    }

    public void validate() {
        // empty implementation
    }

    public List&lt;ValidationError&gt; getErrors() {
        return errors;
    }
}
```

ValidationError class s classic DTO class which has two properties: message and bundleKey.
I think it's good idea to add a new property errorNumber (it's open question for me).

**Listing #6: ValidationError.java**
```
public class ValidationError {

    private String message;

    private String bundleKey;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBundleKey() {
        return bundleKey;
    }

    public void setBundleKey(String bundleKey) {
        this.bundleKey = bundleKey;
    }
}
```

The enum <i>Scope</i> defines possible scopes (<i>REQUEST</i> and <i>SESSION</i>) of request form bean.
I still search a possibility to delete this enum from the project scope...

**Listing #7: Scope.java**
```
public enum Scope {
    REQUEST, SESSION
}
```

The class <i>AbstractMicroServlet</i> is a center class of <b>MicroServlet</b> framework.
It defines lifecycle of <b>MicroServlet</b> application functionality.

**Listing #8: AbstractMicroServlet.java**
```
public abstract class AbstractMicroServlet  extends HttpServlet {

    private static final java.lang.String DISPATCH_PARAMETER_NAME = "method";

    final protected void doGet(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {
        doDispatch(request, response);
    }

    final protected void doPost(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
        doDispatch(request, response);
    }

    protected String getDispachParameterValue(HttpServletRequest request) {
        return request.getParameter(DISPATCH_PARAMETER_NAME);
    }

    final protected void doDispatch(HttpServletRequest request,
                                    HttpServletResponse response)
            throws ServletException, IOException {
        String parameterValue = getDispachParameterValue(request);
        doDispatch(request, response, parameterValue);
    }

    protected void send(HttpServletResponse response,
                        String body)
            throws IOException {
        // It's open question how to implement this method
        PrintWriter out = response.getWriter();
        out.println(body);
    }

    public void doDispatch(HttpServletRequest request,
                           HttpServletResponse response,
                           String parameterValue)
            throws ServletException, IOException {
        if ((parameterValue == null) || ("".equals(parameterValue))) {
            // execute default view
            try {
                Method method = this.getClass().getMethod("doView",
                                    HttpServletRequest.class, HttpServletResponse.class);
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
                    if ((dispatchMethod.checkRegister()
                           && (methodName.equals("do"+parameterValue)))
                        || ((!dispatchMethod.checkRegister())
                           && (methodName.equalsIgnoreCase("do"+parameterValue)))) {
                        executeMethod(method, request, response);
                    }
                } else {
                    // value property of DispatchMethod annotation is defined
                    if ((dispatchMethod.checkRegister()
                           && (annotationValue.equals(parameterValue)))
                        || ((!dispatchMethod.checkRegister())
                           && (annotationValue.equalsIgnoreCase(parameterValue)))) {
                        executeMethod(method, request, response);
                    }
                }
            }
        }
    }

    protected Object[] getMethodParameters(HttpServletRequest request,
                                           HttpServletResponse response) {
        Object methodArguments[] = new Object[2];
        methodArguments[0] = request;
        methodArguments[1] = response;
        return methodArguments;
    }

    final protected void executeMethod(Method method,
                                       HttpServletRequest request,
                                       HttpServletResponse response)
            throws IOException, ServletException {
        // create form
        RenderForm renderFormAnnotation = method.getAnnotation(RenderForm.class);
        if (renderFormAnnotation != null) {
            try {
                RequestForm formBean = RequestFormUtil
                    .getFormBean(renderFormAnnotation, request);
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
            RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(renderJspAnnotation.page());
            //dispatcher.include(request, response);
            dispatcher.forward(request, response);
        }
    }

    protected abstract void doView(HttpServletRequest request,
                                   HttpServletResponse response)
            throws ServletException, IOException;
}
```

The class <i>RequestFormUtil</i> regions service methods for request form beans:
  1. set/get RequestForm to/from the request form attribute,
  1. the method <i>applyRequestValues</i> which is used on <b>Apply Request Values</b> phase,
  1. the method <i>getFormBean</i> which is used on <b>Restore Form Bean</b> phase,
  1. the method <i>executeLifeCycle</i> executes reset and validate methods of the request form.
<b>Remark:</b> The method <i>executeLifeCycle</i> should be renamed.

**Listing #9: RequestFormUtil.java**
```
public class RequestFormUtil {

    private static final java.lang.String FORM_BEAN_PARAMETER_NAME = "RequestFormBean";

    final public static RequestForm getRequestForm(HttpServletRequest request) {
        return (RequestForm) request.getAttribute(FORM_BEAN_PARAMETER_NAME);
    }

    final public static void setRequestForm(HttpServletRequest request,
                                            RequestForm requestForm) {
        request.setAttribute(FORM_BEAN_PARAMETER_NAME, requestForm);
    }

    final public static void applyRequestValues(RequestForm form,
                                                HttpServletRequest request)
            throws IllegalAccessException {
        try {
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

    final public static RequestForm getFormBean(RenderForm renderFormAnnotation,
                                                HttpServletRequest request)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
                   IllegalAccessException, InstantiationException {
        HttpSession session = request.getSession(true);
        RequestForm form = null;
        if (Scope.SESSION.equals(renderFormAnnotation.scope())) {
            form = (RequestForm) session.getAttribute(renderFormAnnotation.name());
        }
        if (form == null) {
            try {
                <b>Class formClass = renderFormAnnotation.clazz();</b> // one of the changes in release 1.1
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

    final protected static void <span style="color: red">executeLifeCycle</span>(RequestForm form,
                                                 HttpServletRequest request)
            throws IllegalAccessException {
        form.reset();
        RequestFormUtil.applyRequestValues(form, request);
        form.validate();
    }
}
```

# About next release #

<a><b>MicroServlet 1.2</b> is planned on <b>the begin of 2012</b>.</a>

<b>Planned features:</b>

1. a new @Transient annotation for request form bean.
> The annotation marks the property or the property setter and protects value change of the property
> during <i>Apply request values</i> phase even the request parameter with the same name exists
> (the request parameter name is equal to the property name).<br />
2. a new @RenderJspOnError annotation.
> The annotation defines the JSP page which will be use if an error occurs.
> It means the request form bean will have at least one validation error.
> In that case the JSP page defined by <i>@RenderJsp</i> annotation will be ignored.
> The annotation can be use in pair with <i>@RenderForm</i> annotation only.


<b>Open questions:</b>

1. Is it make sense to rename the <i>ValidationError</i> class to <i>ErrorMessage</i>?
> Error message can be added by the code in dispatch method during <i>Invoke application</i> phase
> (not only in the <i>validate</i> method of request bean form)
> and the ValidationError class name is confused.<br />
2. How to define default and supported locales and custom name for bundle file(s)?
> Do we have to use annotation for the main class (implementation of <i>AbstractMicroServlet</i> class)?<br />
3. Is it necessary to create a special tag for error messages (validation messages)?


# Conclusion #

The MicroServlet framework was written as an example of very simple MVC framework for my book about Java Web development.
When I start the developing of <a href='http://code.google.com/p/ajaxportal/'>Ajax Portal</a> I understood that it's very helpful for Portlet developing.












