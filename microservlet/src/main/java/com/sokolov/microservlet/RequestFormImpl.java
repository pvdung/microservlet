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
