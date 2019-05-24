package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import db.GoodDao;
import db.impl.GoodDaoImpl;
import model.Book;

@WebServlet("/admin/signupBook")
public class CabinetServletSignupBook extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CabinetServletSignupBook.class);
	private static final GoodDao goodDao = new GoodDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("signupBook.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String year = request.getParameter("year");
		String pages = request.getParameter("pages");
		String imageUrl = request.getParameter("imageUrl");
		String price = request.getParameter("price");

		Book book = new Book(title, author, Integer.parseInt(year), Integer.parseInt(pages), imageUrl, Double.parseDouble(price));
		try {
			goodDao.add(book);
			request.setAttribute("successMessage", "book successfully added");
			request.getRequestDispatcher("signupBook.jsp").forward(request, response);
		} catch (Exception e) {
			LOGGER.debug("problems by adding new book to db", e);
			request.setAttribute("errMessage", "book update failed");
			request.getRequestDispatcher("signupBook.jsp").forward(request, response);
		}
	}
}