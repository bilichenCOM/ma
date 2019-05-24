package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import db.BookDao;
import db.CabinetCrud;
import model.Book;

@WebServlet("/admin/deleteBook")
public class CabinetServletDeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CabinetServletDeleteBook.class);
	private static final CabinetCrud<Book> BOOK_CRUD = new BookDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			BOOK_CRUD.delete(Long.parseLong(id));
			request.setAttribute("successMessage", "book has been deleted");
			request.getRequestDispatcher("adminGoods").forward(request, response);
		} catch (Exception e) {
			LOGGER.debug("problems by deleting", e);
			request.setAttribute("errMessage", "book was not successfully deleted, please try again...");
			request.getRequestDispatcher("adminGoods").forward(request, response);
		}
	}
}