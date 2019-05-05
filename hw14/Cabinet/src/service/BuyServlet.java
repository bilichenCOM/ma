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
import db.PurchaseCRUD;
import db.UserCRUD;
import model.Good;
import model.Purchase;
import model.ShopSession;
import model.User;

@WebServlet("/buy")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final BookCRUD bookCrud = new BookCRUD();
	private static final PurchaseCRUD purchaseCrud = new PurchaseCRUD();
	private static final UserCRUD userCrud = new UserCRUD();

	private static final Logger logger = Logger.getLogger(BuyServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		request.setAttribute("bookId", bookId);
		request.getRequestDispatcher("purchaseVerification").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShopSession shopSession = (ShopSession) request.getSession().getAttribute("shopSession");
		User user = shopSession.getUser();
		String bookIdString = (String) request.getAttribute("bookId");
		try {
			Good book = bookCrud.read(bookIdString).get();
			Double bookPrice = book.getPrice();
			Long userId = user.getId();
			Long bookId = book.getId();

			if (bookPrice > user.getBalance()) {
				request.setAttribute("errMessage", "not enough money!");
				request.getRequestDispatcher("goodsPanel.jsp").forward(request, response);
				return;
			}

			Purchase purchase = new Purchase(bookId, userId, bookPrice);
			purchaseCrud.create(purchase);
			user.purchase(book);
			userCrud.update(user);
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