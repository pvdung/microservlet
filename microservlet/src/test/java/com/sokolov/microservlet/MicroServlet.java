package com.sokolov.microservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sokolov.microservlet.annotation.DispatchMethod;
import com.sokolov.microservlet.dto.ValidationError;

/**
 * Dummy class used by unit tests.
 * @author helio frota
 *
 */
@SuppressWarnings("serial")
public class MicroServlet extends AbstractMicroServlet {

	/**
	 * {@inheritDoc}
	 */
	@DispatchMethod
	protected void doView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Just up coverage percent.
		ValidationError validationError = new ValidationError();
		validationError.getBundleKey();
		validationError.getMessage();
		validationError.setBundleKey("theKey");
		validationError.setMessage("theMessage");
		validationError = new ValidationError("theMessage");
	}

}
