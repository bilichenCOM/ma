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


@WebServlet("/delete")
public class CabinetServletDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CabinetServletDelete.class);
	private static final UserCRUD crud = new UserCRUD();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		if (!request.getSession().getAttribute("logged").equals("true")) {
			response.sendRedirect("login.jsp");
		}
			try {
				crud.delete(email);
				request.setAttribute("successMessage", "user successfully deleted!");
				request.getRequestDispatcher("adminPanel.jsp").forward(request, response);
			} catch (ConnectionException e) {
				logger.debug("connection to db failed!");
				request.setAttribute("errMessage", "connection failed, please try again...");
				request.getRequestDispatcher("adminPanel.jsp").forward(request, response);
			}
	}
}