package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import db.ConnectionException;
import db.UserCrud;


@WebServlet("/admin/deleteUser")
public class CabinetServletDeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CabinetServletDeleteUser.class);
	private static final UserCrud USER_CRUD = new UserCrud();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");

			try {
				USER_CRUD.delete(email);
				request.setAttribute("successMessage", "user successfully deleted!");
				request.getRequestDispatcher("admin").forward(request, response);
			} catch (ConnectionException e) {
				LOGGER.debug("connection to db failed!");
				request.setAttribute("errMessage", "connection failed, please try again...");
				request.getRequestDispatcher("admin").forward(request, response);
			}
	}
}