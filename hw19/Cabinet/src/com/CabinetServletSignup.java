package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import db.CabinetCrud;
import db.ConnectionException;
import db.ExistingUserException;
import db.UserDao;
import model.Role;
import model.User;
import utils.ParametersValidator;
import utils.ShaPasswordGenerator;

@WebServlet("/signup")
public class CabinetServletSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CabinetServletSignup.class);
	private static final CabinetCrud<User> CRUD = new UserDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");

		String passwordFromForm = request.getParameter("password");

		if (
			!ParametersValidator.checkEmailValidity(email)
			|| !ParametersValidator.checkTextValidity(passwordFromForm)
			) {
			request.setAttribute("errMessage", "please check validity of login and password...");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
			return;
		}
		
		String salt = ShaPasswordGenerator.generateSalt();
		String password = ShaPasswordGenerator.getShaPassword(passwordFromForm, salt);

		User user = new User(name, surname, gender, Integer.parseInt(age), email, password, Role.USER.getId(), 0.00);
		user.setSalt(salt);

		try {
			CRUD.add(user);

			request.setAttribute("successMessage", "Successfully registered!");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		} catch (ExistingUserException e) {
			LOGGER.debug("user already registered");
			request.setAttribute("errMessage", "user already registered!");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		} catch (ConnectionException e) {
			LOGGER.error("failed connection to db!");
			request.setAttribute("errMessage", "something went wrong, please try again...");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		} catch (Exception e) {
			LOGGER.debug("something went wrong ", e);
			request.setAttribute("errMessage", "something went wrong, please try again...");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}
}