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

import org.junit.Test;

/**
 * Test class RequestFormImplTest.
 * @author helio frota
 *
 */
public class RequestFormImplTest {

	/**
	 * Attribute requestFormImpl of RequestFormImplTest.
	 */
    private RequestFormImpl requestFormImpl;

    /**
     * Test method doTest.
     */
    @Test
    public void doTest() {
    	requestFormImpl = new RequestFormImpl();
    	requestFormImpl.getErrors();
    	requestFormImpl.reset();
    	requestFormImpl.validate();
    }
}
