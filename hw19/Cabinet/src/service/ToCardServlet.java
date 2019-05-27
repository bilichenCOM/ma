package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.CardDao;
import db.GoodDao;
import db.UserDao;
import db.impl.CardDaoImpl;
import db.impl.GoodDaoImpl;
import db.impl.UserDaoImpl;
import model.Card;
import model.Good;
import model.ShopSession;
import model.User;

@WebServlet("/shop/toCard")
public class ToCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final UserDao userDao = new UserDaoImpl();
	private static final CardDao cardDao = new CardDaoImpl();
	private static final GoodDao goodDao = new GoodDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShopSession shopSession = (ShopSession) request.getSession().getAttribute("shopSession");
		Long goodId = Long.parseLong(request.getParameter("id"));

		User user = shopSession.getUser();
		if (user.getCard() == null) {
			createCard(user);
		}
		Card card = user.getCard();
		Good good = goodDao.read(goodId).get();

		card.addGood(good);
		cardDao.update(card);

		shopSession.setUser(user);
		request.getSession().setAttribute("shopSession", shopSession);
		request.getSession().setAttribute("successMessage", "good successfully added to your basket!");
		response.sendRedirect("/Cabinet/shop");
	}

	private void createCard(User targetUser) {
		Card card = new Card();
		card.setUserId(targetUser.getId());
		targetUser.setCard(card);
		cardDao.add(card);
		userDao.update(targetUser);
	}
}