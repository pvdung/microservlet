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

/**
 * Enum declares REQUEST and SESSION scopes.
 *
 * @author Sergei Sokolov
 * @version 1.0
 */
public enum Scope {
    /** Marks REQUEST scope of form bean. */
    REQUEST,
    /** Marks SESSION scope of form bean. */
    SESSION
}
