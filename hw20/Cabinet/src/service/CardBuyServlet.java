package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.CardDao;
import db.PurchaseDao;
import db.impl.CardDaoImpl;
import db.impl.PurchaseDaoImpl;
import model.Card;
import model.Good;
import model.Purchase;
import model.ShopSession;
import model.User;

@WebServlet("/shop/card/buy")
public class CardBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final PurchaseDao purchaseDao = new PurchaseDaoImpl();
	private static final CardDao cardDao = new CardDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShopSession shopSession = (ShopSession) request.getSession().getAttribute("shopSession");
		User user = shopSession.getUser();
		Card card = shopSession.getUser().getCard();

		if (card.getValue() > user.getBalance()) {
			request.getSession().setAttribute("errMessage", "not enough money to buy all this stuff...");
			response.sendRedirect("/Cabinet/shop/card");
			return;
		}

		Purchase purchase = new Purchase();
		purchase.setUserId(user.getId());
		List<Good> goods = new ArrayList<>(card.getGoods());
		goods.forEach(g -> { user.purchase(g);
							card.removeGood(g);
							purchase.setGoodId(g.getId());
							purchase.setValue(g.getPrice());
							purchaseDao.add(purchase);
							});

		user.setCard(card);
		cardDao.update(card);
		shopSession.setUser(user);
		request.getSession().setAttribute("shopSession", shopSession);
		request.getSession().setAttribute("successMessage", "all has been purchased!");
		response.sendRedirect("/Cabinet/shop/card");
	}

}