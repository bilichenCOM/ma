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

@WebServlet("/update")
public class CabinetServletUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("update_panel.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (!session.getAttribute("auth").equals("authorized")) {
			response.sendRedirect("login.jsp");
			return;
		}

		Map<String, String> userInfo = ParametersValidator.formatParamMap(request.getParameterMap());
		if (!ParametersValidator.checkSignUpInfo(userInfo)) {
			session.setAttribute("errUpdateMessage", "please check validation of input data");
			response.sendRedirect("update_panel.jsp");
			return;
		}

		User updatedUser = new User(userInfo);
		try {
			DBConnector.connect();
			DBConnector.updateUser(updatedUser);
			session.setAttribute("errUpdateMessage", "");
			session.setAttribute("successUpdateMessage", "user data has been successfully updated!");
		} catch (ConnectionException e) {
			session.setAttribute("errUpdateMessage", "failed connection to DB! Please try again...");
		}  finally {
			DBConnector.disconnect();
			response.sendRedirect("update_panel.jsp");
		}
	}
}