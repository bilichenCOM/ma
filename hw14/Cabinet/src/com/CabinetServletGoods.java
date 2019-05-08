package com;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BookCRUD;
import model.Book;

@WebServlet("/adminGoods")
public class CabinetServletGoods extends HttpServlet {

	private static final BookCRUD bookCrud = new BookCRUD();
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> books = bookCrud.readAll();
		request.setAttribute("books", books);
		request.getRequestDispatcher("adminGoods.jsp").forward(request, response);
	}
}
