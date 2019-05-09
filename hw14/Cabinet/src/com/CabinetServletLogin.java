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
import model.Role;
import model.User;
import utils.ShaPasswordGenerator;

@WebServlet("/login")
public class CabinetServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CabinetServletLogin.class);
	private static final UserCrud USER_CRUD = new UserCrud();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String passwordFromForm = request.getParameter("password");

		LOGGER.debug("login attempt with credentials: " + email + " " + passwordFromForm + " from " + request.getLocalAddr());

		if (email.isEmpty() || passwordFromForm.isEmpty()) {
			request.setAttribute("errMessage", "enter your email and password");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		try {
			User user = USER_CRUD.read(email).get();
			
			String salt = user.getSalt();
			String password = ShaPasswordGenerator.getShaPassword(passwordFromForm, salt);

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
			}
			LOGGER.debug("logged as " + user.getRoleId());
		} catch (ConnectionException e) {
			LOGGER.error("failed connection to db", e);
			request.setAttribute("errMessage", "something went wrong, please try again...");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (WrongEmailException e) {
			LOGGER.debug("wrong credentials for " + email);
			request.setAttribute("errMessage", "wrong email or password!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}