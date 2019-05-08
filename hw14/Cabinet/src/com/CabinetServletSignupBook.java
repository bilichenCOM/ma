package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BookCRUD;
import model.Book;

@WebServlet("/signupBook")
public class CabinetServletSignupBook extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final BookCRUD bookCrud = new BookCRUD();

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
		bookCrud.create(book);
		request.setAttribute("successMessage", "book successfully added");
		request.getRequestDispatcher("signupBook.jsp").forward(request, response);
	}
}