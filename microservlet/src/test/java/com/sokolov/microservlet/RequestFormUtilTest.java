package com.sokolov.microservlet;

import javax.servlet.http.HttpServletRequest;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.sokolov.microservlet.annotation.RenderForm;

/**
 * Test class RequestFormUtilTest.
 * @author helio frota
 *
 */
public class RequestFormUtilTest {

	/**
	 * Attribute httpServletRequest of RequestFormUtilTest.
	 */
	private HttpServletRequest httpServletRequest;

	/**
	 * Attribute requestForm of RequestFormUtilTest.
	 */
	private RequestForm requestForm;

	/**
	 * Default before junit method.
	 */
	@Before
	public void setup() {
		httpServletRequest = Mockito.mock(HttpServletRequest.class);
        httpServletRequest.setAttribute("RequestFormBean", new RequestFormImpl());
        requestForm = RequestFormUtil.getRequestForm(httpServletRequest);
	}

	/**
	 * Test method getRequestForm.
	 */
	@Test
    public void getRequestForm() {
        RequestFormUtil.setRequestForm(httpServletRequest, requestForm);
    }

	/**
	 * Test method applyRequestValues.
	 */
	@Test
	public void applyRequestValues() {
		try {
		    RequestFormUtil.applyRequestValues(new RequestFormImpl(), httpServletRequest);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method executeLifeCycle.
	 */
	@Test
	public void executeLifeCycle() {
		try {
		    RequestFormUtil.executeLifeCycle(new RequestFormImpl(), httpServletRequest);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method getFormBean.
	 */
	@Test
	public void getFormBean() {
		try {
			Assert.assertEquals("form", RenderForm.class.getMethod("name").getDefaultValue());
			Assert.assertEquals(Scope.REQUEST, RenderForm.class.getMethod("scope").getDefaultValue());
		    RequestFormUtil.getFormBean(RenderForm.class.getAnnotation(RenderForm.class), httpServletRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
