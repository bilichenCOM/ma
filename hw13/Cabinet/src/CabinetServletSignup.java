import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")

@WebServlet("/signup")
public class CabinetServletSignup extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> signupInfo = ParametersValidator.formatParamMap(request.getParameterMap());

		String email = signupInfo.get("email");
		String passwd = signupInfo.get("passwd");
		
		if( !ParametersValidator.isValidCredentials(email, passwd)) {
			response.sendRedirect("wrong_credentials.html");
			return;
		}

		UserDataBase userDataBase = UserDataBase.getInstance();
		if (userDataBase.addUniqueUser(email, signupInfo)) {
			response.sendRedirect("registered.html");
			return;
		}
		response.sendRedirect("existing_user.html");
	}
}