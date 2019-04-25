package com;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebServlet("/signup")
public class CabinetServletSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> signupInfo = ParametersValidator.formatParamMap(request.getParameterMap());

		if (!ParametersValidator.checkSignUpInfo(signupInfo)) {
			response.sendRedirect("wrong_credentials.html");
			return;
		}

		User user = new User(signupInfo);

		try {
			DBConnector.connect();
			DBConnector.addUser(user);
			response.sendRedirect("registered.html");
		} catch (ExistingUserException e) {
			response.sendRedirect("existing_user.html");
		} catch (ConnectionException e) {
			System.err.println("failed connection to database!");
		} finally {
			DBConnector.disconnect();
		}
	}
}