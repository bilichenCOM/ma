package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import db.ConnectionException;
import db.ExistingUserException;
import db.UserCrud;
import model.Role;
import model.User;
import utils.ParametersValidator;

@WebServlet("/signup")
public class CabinetServletSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CabinetServletSignup.class);
	private static final UserCrud crud = new UserCrud();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if (
			!ParametersValidator.checkEmailValidity(email)
			|| !ParametersValidator.checkTextValidity(password)
			) {
			request.setAttribute("errMessage", "please check validity of login and password...");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
			return;
		}
		
		User user = new User(name, surname, gender, Integer.parseInt(age), email, password, Role.USER.getId(), 0.00);

		try {
			crud.create(user);

			request.setAttribute("successMessage", "Successfully registered!");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		} catch (ExistingUserException e) {
			logger.debug("user already registered");
			request.setAttribute("errMessage", "user already registered!");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		} catch (ConnectionException e) {
			logger.error("failed connection to db!");
			request.setAttribute("errMessage", "something went wrong, please try again...");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}
}