package com.sokolov.microservlet.dto;

/**
 * DTO for validation error.
 *
 * @author Sergei Sokolov
 * @version 1.0
 */
public class ValidationError {

    /** Holds message property. */
    private String message;

    /** Holds message property. */
    private String bundleKey;

    /**
     * Default constructor.
     */
    public ValidationError() {

    }

    /**
     * Constructor with the message parameter.
     */
    public ValidationError(String message) {
        this.message = message;
    }

    /**
     * Getter for message property.
     *
     * @return String a value of message property
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter for message property.
     *
     * @param message a new value of message property
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter for bundleKey property.
     *
     * @return String a value of bundleKey property
     */
    public String getBundleKey() {
        return bundleKey;
    }

    /**
     * Setter for bundleKey property.
     *
     * @param bundleKey a new value of bundleKey property
     */
    public void setBundleKey(String bundleKey) {
        this.bundleKey = bundleKey;
    }
}
