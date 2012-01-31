package com.sokolov.microservlet.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Defines the executed method and dispatch value.
 *
 * @author Sergei Sokolov
 * @version 1.0
 */
@Retention (value = RetentionPolicy.RUNTIME)
public @interface DispatchMethod {

    /** Holds dispatch value. */
    String value() default "";

    /** Holds flag - Check register of a dispatch value or not. */
    boolean checkRegister() default false;
}
