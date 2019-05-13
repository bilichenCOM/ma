package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import db.BookCrud;
import db.ConnectionException;
import db.PurchaseCrud;
import db.UserCrud;
import model.Good;
import model.Purchase;
import model.ShopSession;
import model.User;

@WebServlet("/shop/buy")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final BookCrud BOOK_CRUD = new BookCrud();
	private static final PurchaseCrud PURCHASE_CRUD = new PurchaseCrud();
	private static final UserCrud USER_CRUD = new UserCrud();

	private static final Logger LOGGER = Logger.getLogger(BuyServlet.class);

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
			Good book = BOOK_CRUD.read(bookIdString).get();
			Double bookPrice = book.getPrice();
			Long userId = user.getId();
			Long bookId = book.getId();

			if (bookPrice > user.getBalance()) {
				request.getSession().setAttribute("errMessage", "not enough money!");
				response.sendRedirect("/Cabinet/shop");
				return;
			}

			Purchase purchase = new Purchase(bookId, userId, bookPrice);
			PURCHASE_CRUD.create(purchase);
			user.purchase(book);
			USER_CRUD.update(user);
			LOGGER.debug("Book: " + book + " - has been purchased");
			request.getSession().setAttribute("successMessage", "purchase has been done");
			response.sendRedirect("/Cabinet/shop");
		} catch (ConnectionException e) {
			LOGGER.debug("connection failed");
			request.getSession().setAttribute("errMessage", "connection failed");
			response.sendRedirect("/Cabinet/shop");
		}
	}
}