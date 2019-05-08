package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BookCRUD;

@WebServlet("/deleteBook")
public class CabinetServletDeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final BookCRUD bookCrud = new BookCRUD();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		bookCrud.delete(id);
		request.setAttribute("successMessage", "book has been deleted");
		request.getRequestDispatcher("adminGoods").forward(request, response);
	}
}