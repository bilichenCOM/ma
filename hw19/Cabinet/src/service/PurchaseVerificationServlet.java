package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ShopSession;
import model.User;
import utils.VerificationCode;

@WebServlet("/shop/purchaseVerification")
public class PurchaseVerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShopSession shopSession = (ShopSession) request.getSession().getAttribute("shopSession");
		User user = shopSession.getUser();
		String email = user.getEmail();
		String bookId = (String) request.getAttribute("bookId");

		int verCode = VerificationCode.generate();
		MailService.sendMessage(email, "Purchase verification code: " + verCode);
		request.setAttribute("verCode", verCode);
		request.setAttribute("bookId", bookId);
		request.getRequestDispatcher("purchaseVerification.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String verCode = request.getParameter("verCode");
		String code = request.getParameter("code");
		String bookId = request.getParameter("bookId");
		
		if (verCode.equals(code)) {
			request.setAttribute("bookId", bookId);
			request.getRequestDispatcher("buy").forward(request, response);
		} else {
			request.getSession().setAttribute("errMessage", "failed verification, please try again...");
			response.sendRedirect("/Cabinet/shop");
		}
	}
}