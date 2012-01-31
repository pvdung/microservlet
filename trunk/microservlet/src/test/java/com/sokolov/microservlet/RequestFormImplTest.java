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
