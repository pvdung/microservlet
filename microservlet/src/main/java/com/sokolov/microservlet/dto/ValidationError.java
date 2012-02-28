/*
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
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
