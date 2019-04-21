import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")

@WebServlet("/signup")
public class CabinetServletSignup extends HttpServlet {
	private static final Map<String, Map<String, String>> userInfo = new HashMap<>();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String> params = formatParamMap(request.getParameterMap());
		PrintWriter out = response.getWriter();

		String email = params.get("email");
		String passwd = params.get("passwd");

		if (!isValidCredentials(email, passwd)) {
			response.sendRedirect("wrong_credentials.html");
			return;
		}

		System.out.println("adding new user....");
		try {
			addUser(email, params);
		} catch (ExistingUserException e) {
			out.println("<div style='color:red'>User with this email already exist</div>");
			return;
		}
		System.out.printf("user %s added!\r\n", email);
		response.sendRedirect("registered.html");
		
	}

	private Map<String, String> formatParamMap(Map<String, String[]> params) {
		Map<String, String> formatParams = new HashMap<>();
		params.keySet().stream()
			.forEach(k -> formatParams.put(k, params.get(k)[0]));
		return formatParams;
	}

	private boolean isValidCredentials(String email, String passwd) {
		return Pattern.matches("\\w+@\\w+\\.\\w+", email) && Pattern.matches("\\w+", passwd);
	}

	private boolean addUser(String email, Map<String, String> info) throws ExistingUserException {
		if (userInfo.containsKey(email)) {
			throw new ExistingUserException();
		}
		userInfo.put(email, info);
		return true;
	}

	public Map<String, String> getUserInfo(String email, String passwd) throws WrongCredentialsException {
		if (!checkCredentials(email, passwd)) {
			throw new WrongCredentialsException();
		}
		return userInfo.get(email);
	}

	public boolean checkCredentials(String email, String passwd) {
		if (userInfo.containsKey(email) && userInfo.get(email).get("passwd").equals(passwd)) {
			return true;
		}
		return false;
	}
}