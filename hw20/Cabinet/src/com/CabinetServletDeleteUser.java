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


@WebServlet("/admin/deleteUser")
public class CabinetServletDeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CabinetServletDeleteUser.class);
	private static final UserDao USER_CRUD = new UserDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

			try {
				USER_CRUD.delete(Long.parseLong(id));
				request.getSession().setAttribute("successMessage", "user successfully deleted!");
				response.sendRedirect("/Cabinet/admin");
			}  catch (Exception e) {
				LOGGER.debug("problems by deleting users", e);
				request.setAttribute("errMessage", "user not deleted, please try again...");
				request.getRequestDispatcher("/admin").forward(request, response);
			}
	}
}