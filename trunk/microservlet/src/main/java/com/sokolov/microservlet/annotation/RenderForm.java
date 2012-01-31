package com.sokolov.microservlet.annotation;

import com.sokolov.microservlet.Scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Defines the form bean.
 *
 * @author Sergei Sokolov
 * @version 1.0
 */
@Retention (value = RetentionPolicy.RUNTIME)
public @interface RenderForm {

    /** Holds form bean name (defual value is "form"). */
    String name() default "form";

    /** Holds form bean class (required parameter). */
    Class clazz();

    /** Holds form bean scope (default scope is REQUEST). */
    Scope scope() default Scope.REQUEST;
}
