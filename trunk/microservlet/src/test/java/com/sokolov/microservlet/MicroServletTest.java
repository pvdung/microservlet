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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * Test class MicroServletTest.
 * @author helio frota
 *
 */
public class MicroServletTest {

	/**
	 * Attribute httpServletRequest of MicroServletTest.
	 */
	private HttpServletRequest httpServletRequest;
	/**
	 * Attribute httpServletResponse of MicroServletTest.
	 */
	private HttpServletResponse httpServletResponse;

	/**
	 * Attribute microServlet of MicroServletTest.
	 */
	private MicroServlet microServlet;

	/**
	 * Test method doView.
	 */
	@Test
	public void doView() {
		httpServletRequest = Mockito.mock(HttpServletRequest.class);
		httpServletResponse = Mockito.mock(HttpServletResponse.class);
		microServlet = new MicroServlet();
		try {
		    microServlet.doView(httpServletRequest, httpServletResponse);
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ServletException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method doDispatch.
	 */
	@Test
	public void doDispatch() {
		httpServletRequest = Mockito.mock(HttpServletRequest.class);
		httpServletResponse = Mockito.mock(HttpServletResponse.class);
		microServlet = new MicroServlet();
		try {
		    microServlet.doDispatch(httpServletRequest, httpServletResponse);
		    microServlet.doDispatch(httpServletRequest, httpServletResponse, "test");
		    microServlet.doDispatch(httpServletRequest, httpServletResponse, "");
		    microServlet.doDispatch(httpServletRequest, httpServletResponse, null);
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ServletException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method send.
	 */
	@Test
	public void send() {
		httpServletRequest = Mockito.mock(HttpServletRequest.class);
		microServlet = new MicroServlet();
		microServlet.getDispachParameterValue(httpServletRequest);
	}

	/**
	 * Test method doPost.
	 */
	@Test
	public void doPost() {
		httpServletRequest = Mockito.mock(HttpServletRequest.class);
		httpServletResponse = Mockito.mock(HttpServletResponse.class);
		microServlet = new MicroServlet();
		try {
			microServlet.doPost(httpServletRequest, httpServletResponse);
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ServletException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method getMethodParameters.
	 */
	@Test
	public void getMethodParameters() {
		httpServletRequest = Mockito.mock(HttpServletRequest.class);
		httpServletResponse = Mockito.mock(HttpServletResponse.class);
		microServlet = new MicroServlet();
		microServlet.getMethodParameters(httpServletRequest, httpServletResponse);
	}

}
