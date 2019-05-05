package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import db.ConnectionException;
import db.UserCRUD;
import db.WrongEmailException;
import model.User;

@WebServlet("/update")
public class CabinetServletUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CabinetServletUpdate.class);
	private static final UserCRUD crud = new UserCRUD();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");

		try {
			User user = crud.read(email).get();

			request.setAttribute("user", user);
			request.getRequestDispatcher("update_panel.jsp").forward(request, response);
		} catch (WrongEmailException e) {
			logger.debug("wrong email " + email + " - user not updated!");
			request.setAttribute("errMessage", "user cannot be updated");
			request.getRequestDispatcher("adminPanel.jsp").forward(request, response);
		} catch (ConnectionException e) {
			logger.error("connection to db failed");
			request.setAttribute("errMessage", "connection failed, please try again...");
			request.getRequestDispatcher("adminPanel.jsp");
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
			crud.update(user);
			request.setAttribute("user", user);
			request.setAttribute("successMessage", "user successfully updated");
			request.getRequestDispatcher("update_panel.jsp").forward(request, response);
		} catch (ConnectionException e) {
			logger.error("connection to db failed");
			request.setAttribute("errMessage", "connection to db failed, please try again...");
			request.getRequestDispatcher("update_panel.jsp").forward(request, response);
		}
	}
}