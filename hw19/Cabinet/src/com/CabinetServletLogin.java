package com;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import db.impl.UserDaoImpl;
import model.Role;
import model.User;
import utils.ShaPasswordGenerator;

@WebServlet("/login")
public class CabinetServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CabinetServletLogin.class);
	private static final UserDaoImpl USER_CRUD = new UserDaoImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String passwordFromForm = request.getParameter("password");

		LOGGER.debug("login attempt with credentials: " + email + " " + passwordFromForm + " from "
				+ request.getLocalAddr());

		if (email.isEmpty() || passwordFromForm.isEmpty()) {
			request.setAttribute("errMessage", "enter your email and password");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		Optional<User> optionalUser = Optional.empty();

		try {
			optionalUser = USER_CRUD.readByEmail(email);			
		} catch (Exception e) {
			request.getSession().setAttribute("errMessage", "something went wrong... please try again");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		if (!optionalUser.isPresent()) {
			LOGGER.debug("wrong credentials for " + email);
			request.setAttribute("errMessage", "wrong email or password!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		User user = optionalUser.get();

		String salt = user.getSalt();
		String password = ShaPasswordGenerator.getSha256Password(passwordFromForm, salt);

		if (!user.getPassword().equals(password)) {
			LOGGER.debug("wrong credentials for " + email);
			request.setAttribute("errMessage", "wrong email or password!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		request.getSession().setAttribute("logged", "true");
		request.getSession().setAttribute("user", user);

		if (user.getRoleId() == Role.USER.getId()) {
			response.sendRedirect("user");
		} else if (user.getRoleId() == Role.ADMIN.getId()) {
			response.sendRedirect("admin");
		} else {
			LOGGER.debug("not defined role for user " + user);
			request.getSession().setAttribute("errMessage", "your role is not defined... please ask administrator...");
			response.sendRedirect(".");
		}

		LOGGER.debug("logged as " + user.getRoleId());
	}
}