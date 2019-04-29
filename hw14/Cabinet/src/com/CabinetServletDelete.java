package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/delete")
public class CabinetServletDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("auth").equals("authorized")) {
			try {
				DBConnector.connect();
				DBConnector.deleteUser(request.getParameter("email"));
				response.sendRedirect("panel.jsp");
			} catch (ConnectionException e) {
				System.err.println("connection error!");
				response.sendRedirect("panel.jsp");
			} finally {
				DBConnector.disconnect();
			}
		} else {
			response.sendRedirect("login.jsp");
		}
	}
}