package com.sokolov.microservlet;

import com.sokolov.microservlet.dto.ValidationError;

import java.util.List;
import java.util.ArrayList;

/**
 * Default implementatio of RequestForm.
 *
 * @author Sergei Sokolov
 * @version 1.0
 */
public class RequestFormImpl implements RequestForm {

    /** Holds a list of validation errors. */
    private List<ValidationError> errors = new ArrayList<ValidationError>();

    /**
     * {@inheritDoc}
     */
    public void reset() {
        // empty implementation
    }

    /**
     * {@inheritDoc}
     */
    public void validate() {
        // empty implementation
    }

    /**
     * {@inheritDoc}
     */
    public List<ValidationError> getErrors() {
        return errors;
    }
}
