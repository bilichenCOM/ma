package com;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

@WebServlet("/signup")
public class CabinetServletSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> signupInfo = ParametersValidator.formatParamMap(request.getParameterMap());
		HttpSession session = request.getSession();

		if (!ParametersValidator.checkSignUpInfo(signupInfo)) {
			session.setAttribute("errSignupMessage", "please validate input data");
			response.sendRedirect("signup.jsp");
			return;
		}

		User user = new User(signupInfo);

		try {
			DBConnector.connect();
			DBConnector.addUser(user);
			session.setAttribute("errSignupMessage", "");
			session.setAttribute("successSignupMessage", "Congratulations! You are now registered user!");
		} catch (ExistingUserException e) {
			session.setAttribute("errSignupMessage", "user with the same email is registered, please login");
		} catch (ConnectionException e) {
			session.setAttribute("errSignupMessage", "connection to DB failed!");
		} finally {
			DBConnector.disconnect();
			response.sendRedirect("signup.jsp");
		}
	}
}