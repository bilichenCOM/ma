import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")

@WebServlet("/login")
public class CabinetServletLogin extends HttpServlet {
	private static final CabinetServletSignup userManager = new CabinetServletSignup();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");

		Map<String, String> userInfo = new HashMap<>();
		try {
			userInfo = userManager.getUserInfo(email, passwd);
		} catch (WrongCredentialsException e) {
			response.sendRedirect("wrong_credentials.html");
			return;
		}

		String title = getUserTitle(userInfo);
		String name = getUserName(userInfo);
		String surname = getUserSurname(userInfo);

		StringBuilder message = new StringBuilder();
		message.append("Hello! ").append(String.format("%s %s %s</br>", title, name, surname)).append("How are you today???");

		PrintWriter out = response.getWriter();
		out.println(message);
	}

	private String getUserSurname(Map<String, String> userInfo) {
		return userInfo.get("surname");
	}

	private String getUserName(Map<String, String> userInfo) {
		return userInfo.get("name") != "" ? userInfo.get("name") :  userInfo.get("email");
	}

	private String getUserTitle(Map<String, String> userInfo) {
		if (!userInfo.containsKey("gender") || userInfo.get("gender") == null) {
			return " ";
		}

		if (userInfo.get("gender").equals("female")) {
			return "Miss";
		} else  if (userInfo.get("gender").equals("male")) {
			return "Mr.";
		}
		return "";
	}
}