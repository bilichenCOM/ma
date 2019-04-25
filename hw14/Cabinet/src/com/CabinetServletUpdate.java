package com;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebServlet("/update")
public class CabinetServletUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!request.getSession().getAttribute("logged").equals("true")) {
			response.sendRedirect("login.html");
			return;
		}

		Map<String, String> userInfo = ParametersValidator.formatParamMap(request.getParameterMap());
		if (!ParametersValidator.checkSignUpInfo(userInfo)) {
			response.sendRedirect("wrong_credentials.html");
			return;
		}

		User updatedUser = new User(userInfo);
		try {
			DBConnector.connect();
			DBConnector.updateUser(updatedUser);
			response.sendRedirect("panel.jsp");
		} catch (ConnectionException e) {
			System.err.println("connection failed!");
			response.sendRedirect("update_panel.jsp");
		} catch (WrongCredentialsException e) {
			System.err.println("wrong credentials!");
			response.sendRedirect("wrong_credentials.html");
		}

	}
}