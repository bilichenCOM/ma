package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import db.BookCRUD;
import db.ConnectionException;
import model.Good;
import model.ShopSession;
import model.User;

@WebServlet("/buy")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final BookCRUD crud = BookCRUD.getInstance();
	private static final Logger logger = Logger.getLogger(BuyServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		request.setAttribute("bookId", bookId);
		request.getRequestDispatcher("purchaseVerification").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShopSession shopSession = (ShopSession) request.getSession().getAttribute("user");
		User user = shopSession.getUser();
		String bookId = (String) request.getAttribute("bookId");
		try {
			Good book = crud.read(bookId).get();
			user.purchase(book);
			logger.debug("Book: " + book + " - has been purchased");
			request.setAttribute("successMessage", "purchase has been done");
			request.getRequestDispatcher("goodsPanel.jsp").forward(request, response);
		} catch (ConnectionException e) {
			logger.debug("connection failed");
			request.setAttribute("errMessage", "connection failed");
			request.getRequestDispatcher("goodsPanel.jsp").forward(request, response);
		}
	}
}