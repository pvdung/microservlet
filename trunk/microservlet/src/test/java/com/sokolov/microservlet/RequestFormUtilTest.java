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
