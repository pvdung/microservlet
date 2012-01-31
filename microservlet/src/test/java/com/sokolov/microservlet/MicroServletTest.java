package com.sokolov.microservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.asm.MethodAdapter;

import com.sokolov.microservlet.annotation.RenderForm;

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
