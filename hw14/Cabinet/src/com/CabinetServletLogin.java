package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class CabinetServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");

		if (!ParametersValidator.checkEmailValidity(email) || !ParametersValidator.checkTextValidity(passwd)) {
			response.sendRedirect("wrong_credentials.html");
			return;
		}

		try {
			DBConnector.connect();
			DBConnector.getUser(email, passwd);
			request.getSession().setMaxInactiveInterval(3000);
			request.getSession().setAttribute("logged", "true");
			request.getRequestDispatcher("panel.jsp").forward(request, response);
		} catch (ConnectionException e) {
			System.err.println("no connection to database!");
			response.sendRedirect("login.html");
		} catch (WrongCredentialsException e) {
			response.sendRedirect("wrong_credentials.html");
		} finally {
			DBConnector.disconnect();
		}
	}
}