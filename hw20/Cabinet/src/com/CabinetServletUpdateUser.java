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
import model.User;

@WebServlet("/admin/updateUser")
public class CabinetServletUpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CabinetServletUpdateUser.class);
	private static final UserDao USER_CRUD = new UserDaoImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		try {
			User user = USER_CRUD.read(Long.parseLong(id)).get();

			request.getSession().setAttribute("userToUpdate", user);
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		} catch (Exception e) {
			LOGGER.error("problems updating user", e);
			request.setAttribute("errMessage", "something went wrong, please try again...");
			request.getRequestDispatcher("/admin").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		Integer age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		Integer roleId = Integer.parseInt(request.getParameter("roleId"));
		Double balance = Double.parseDouble(request.getParameter("balance"));
		
		User user = (User) request.getSession().getAttribute("userToUpdate");
		user.setName(name);
		user.setSurname(surname);
		user.setAge(age);
		user.setGender(gender);
		user.setRoleId(roleId);
		user.setBalance(balance);

		try {
			USER_CRUD.update(user);
			request.setAttribute("user", user);
			request.setAttribute("successMessage", "user successfully updated");
			request.getSession().setAttribute("userToUpdate", null);
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		} catch (Exception e) {
			LOGGER.error("problems updating user", e);
			request.setAttribute("errMessage", "something went wrong, please try again...");
			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		}
	}
}