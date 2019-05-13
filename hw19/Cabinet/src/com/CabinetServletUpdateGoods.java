package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BookCrud;
import model.Book;

@WebServlet("/admin/updateBook")
public class CabinetServletUpdateGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final BookCrud bookCrud = new BookCrud();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Book book = bookCrud.read(id).get();
		request.setAttribute("book", book);
		request.getRequestDispatcher("updateBookPanel.jsp").forward(request, response);
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
		bookCrud.update(book);
		request.setAttribute("successMessage", "successfully updated info");
		request.getRequestDispatcher("updateBookPanel.jsp").forward(request, response);
	}
}