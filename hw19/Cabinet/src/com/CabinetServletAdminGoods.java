package com;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BookCrud;
import model.Book;

@WebServlet("/admin/adminGoods")
public class CabinetServletAdminGoods extends HttpServlet {

	private static final BookCrud BOOK_CRUD = new BookCrud();
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> books = BOOK_CRUD.readAll();
		request.setAttribute("books", books);
		request.getRequestDispatcher("adminGoods.jsp").forward(request, response);
	}
}
