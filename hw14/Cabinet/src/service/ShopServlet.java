package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import db.BookCRUD;
import db.ConnectionException;
import model.Book;
import model.ShopSession;
import model.User;

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ShopServlet.class);
	private static final BookCRUD crud = new BookCRUD();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Book> books = crud.readAll();
			User user = (User) request.getSession().getAttribute("user");
			ShopSession shopSession = new ShopSession();
			shopSession.setBooks(books);
			shopSession.setUser(user);
			request.getSession().setAttribute("shopSession", shopSession);
			request.getRequestDispatcher("goodsPanel.jsp").forward(request, response);
		} catch (ConnectionException e) {
			logger.debug("connection failed", e);
			request.getRequestDispatcher("userPanel.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}