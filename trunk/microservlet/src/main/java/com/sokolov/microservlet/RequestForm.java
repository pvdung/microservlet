package com.sokolov.microservlet;

import java.util.List;

/**
 * Defines main methods of form bean.
 *
 * @author Sergei Sokolov
 * @version 1.0
 */
public interface RequestForm {

    /**
     * Reset properties of (e.g. checkbox parameter of form bean).
     */
    void reset();

    /**
     * Validate request parameter.
     */
    void validate();

    /**
     * Returns a list of validation errors.
     *
     * @return List<com.sokolov.microservlet.dto.ValidationError> a list of validation errors
     */
    List<com.sokolov.microservlet.dto.ValidationError> getErrors();
}
