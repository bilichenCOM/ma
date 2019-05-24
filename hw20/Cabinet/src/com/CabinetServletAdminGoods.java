package com;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.GoodDao;
import db.impl.GoodDaoImpl;
import model.Good;

@WebServlet("/admin/adminGoods")
public class CabinetServletAdminGoods extends HttpServlet {

	private static final GoodDao goodDao = new GoodDaoImpl();
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Good> books = goodDao.readAll();
		request.setAttribute("books", books);
		request.getRequestDispatcher("adminGoods.jsp").forward(request, response);
	}
}
