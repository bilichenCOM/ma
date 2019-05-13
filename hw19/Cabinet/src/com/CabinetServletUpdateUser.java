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
import db.WrongEmailException;
import model.User;

@WebServlet("/admin/updateUser")
public class CabinetServletUpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CabinetServletUpdateUser.class);
	private static final UserCrud USER_CRUD = new UserCrud();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");

		try {
			User user = USER_CRUD.read(email).get();

			request.setAttribute("user", user);
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		} catch (WrongEmailException e) {
			LOGGER.debug("wrong email " + email + " - user not updated!");
			request.setAttribute("errMessage", "user cannot be updated");
			request.getRequestDispatcher("admin").forward(request, response);
		} catch (ConnectionException e) {
			LOGGER.error("connection to db failed");
			request.setAttribute("errMessage", "connection failed, please try again...");
			request.getRequestDispatcher("admin");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String roleId = request.getParameter("roleId");
		String balance = request.getParameter("balance");
		
		User user = new User(name, surname, gender, Integer.parseInt(age), email, password,
				Integer.parseInt(roleId), Double.parseDouble(balance));

		try {
			USER_CRUD.update(user);
			request.setAttribute("user", user);
			request.setAttribute("successMessage", "user successfully updated");
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		} catch (ConnectionException e) {
			LOGGER.error("connection to db failed");
			request.setAttribute("errMessage", "connection to db failed, please try again...");
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		}
	}
}