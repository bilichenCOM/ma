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

@WebServlet("/admin/updateBook")
public class CabinetServletUpdateGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CabinetServletUpdateGoods.class);
	private static final CabinetCrud<Book> BOOK_CRUD = new BookDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			Book book = BOOK_CRUD.read(Long.parseLong(id)).get();
			request.setAttribute("book", book);
			request.getRequestDispatcher("updateBook.jsp").forward(request, response);
		} catch (Exception e) {
			LOGGER.debug("book getting failed", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String year = request.getParameter("year");
		String pages = request.getParameter("pages");
		String imageUrl = request.getParameter("imageUrl");
		String price = request.getParameter("price");
		
		Book book = new Book(Long.parseLong(id), title, author, Integer.parseInt(year),
				Integer.parseInt(pages), imageUrl, Double.parseDouble(price));
		try {
			BOOK_CRUD.update(book);
			request.setAttribute("successMessage", "successfully updated info");
			request.getRequestDispatcher("updateBook.jsp").forward(request, response);
		} catch (Exception e) {
			LOGGER.debug("failed update of book", e);
		}
	}
}