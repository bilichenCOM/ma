package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import db.UserDao;
import db.impl.UserDaoImpl;
import model.Role;
import model.User;
import utils.ParametersValidator;
import utils.ShaPasswordGenerator;

@WebServlet("/signup")
public class CabinetServletSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CabinetServletSignup.class);
	private static final UserDao CRUD = new UserDaoImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (
				!ParametersValidator.checkEmailValidity(request.getParameter("email"))
				|| !ParametersValidator.checkTextValidity(request.getParameter("password"))
				) {
				request.setAttribute("errMessage", "please check validity of login and password...");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
				return;
			}

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");

		String passwordFromForm = request.getParameter("password");
		String salt = ShaPasswordGenerator.generateSalt();
		String password = ShaPasswordGenerator.getSha256Password(passwordFromForm, salt);

		User user = new User(name, surname, gender, Integer.parseInt(age), email, password, Role.USER.getId(), 0.00, salt);

		try {
			CRUD.add(user);

			request.setAttribute("successMessage", "Successfully registered!");
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