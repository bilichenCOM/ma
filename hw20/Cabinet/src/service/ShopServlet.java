package service;

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
import model.ShopSession;
import model.User;

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final GoodDao goodDao = new GoodDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Good> goods = goodDao.readAll();
		User user = (User) request.getSession().getAttribute("user");
		ShopSession shopSession = new ShopSession();
		shopSession.setGoods(goods);
		shopSession.setUser(user);
		request.getSession().setAttribute("shopSession", shopSession);
		request.getRequestDispatcher("shop/shop.jsp").forward(request, response);
	}
}