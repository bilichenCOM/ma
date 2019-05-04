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
import model.Role;
import model.User;

@WebServlet("/login")
public class CabinetServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CabinetServletLogin.class);
	private static final UserCRUD crud = UserCRUD.getInstance();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		logger.debug("login attempt with credentials: " + email + " " + password + " from " + request.getLocalAddr());

		if (email.isEmpty() || password.isEmpty()) {
			request.setAttribute("errMessage", "enter your email and password");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		try {
			User user = crud.read(email).get();

			if (!user.getPassword().equals(password)) {
				logger.debug("wrong credentials for " + email);
				request.setAttribute("errMessage", "wrong email or password!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}

			request.getSession().setAttribute("logged", "true");
			request.getSession().setAttribute("user", user);

			if (user.getRoleId() == Role.USER.getId()) {
				request.getRequestDispatcher("userPanel.jsp").forward(request, response);	
			} else if (user.getRoleId() == Role.ADMIN.getId()) {
				request.getRequestDispatcher("adminPanel.jsp").forward(request, response);
			}
			logger.debug("logged as " + user.getRoleId());
		} catch (ConnectionException e) {
			logger.error("failed connection to db", e);
			request.setAttribute("errMessage", "something went wrong, please try again...");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} catch (WrongEmailException e) {
			logger.debug("wrong credentials for " + email, e);
			request.setAttribute("errMessage", "wrong email or password!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}