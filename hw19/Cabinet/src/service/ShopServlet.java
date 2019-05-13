package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import db.BookCrud;
import db.CabinetCrud;
import db.ConnectionException;
import model.Book;
import model.ShopSession;
import model.User;

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ShopServlet.class);
	private static final CabinetCrud<Book> BOOK_CRUD = new BookCrud();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Book> books = BOOK_CRUD.readAll();
			User user = (User) request.getSession().getAttribute("user");
			ShopSession shopSession = new ShopSession();
			shopSession.setGoods(books);
			shopSession.setUser(user);
			request.getSession().setAttribute("shopSession", shopSession);
			request.getRequestDispatcher("shop/shop.jsp").forward(request, response);
		} catch (ConnectionException e) {
			LOGGER.debug("connection failed", e);
			request.getRequestDispatcher("user").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}