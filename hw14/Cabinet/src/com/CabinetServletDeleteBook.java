package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BookCrud;

@WebServlet("/admin/deleteBook")
public class CabinetServletDeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final BookCrud bookCrud = new BookCrud();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		bookCrud.delete(id);
		request.setAttribute("successMessage", "book has been deleted");
		request.getRequestDispatcher("adminGoods").forward(request, response);
	}
}