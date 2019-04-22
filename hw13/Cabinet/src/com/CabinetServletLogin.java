package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")

@WebServlet("/login")
public class CabinetServletLogin extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");

		UserDataBase userDataBase = UserDataBase.getInstance();
		if (!userDataBase.checkCredentials(email, passwd)) {
			response.sendRedirect("wrong_credentials.html");
			return;
		}

		Map<String, String> userInfo = userDataBase.getUserInfo(email, passwd);
		UserInfoText infoText = new UserInfoText(userInfo);
		String textResponse = infoText.buildTextResponse();

		PrintWriter out = response.getWriter();
		out.println("<html><head></head><body>");
		out.println(textResponse);
		out.println("</body></html>");
	}
}