package com.sokolov.microservlet.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Defines the render JSP.
 *
 * @author Sergei Sokolov
 * @version 1.0
 */
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RenderJsp {
    /** Holds path to JSP page. */
    String page();
}
