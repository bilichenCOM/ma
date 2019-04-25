package com;

import java.util.Map;

public class UserInfoText {
	private Map<String, String> userInfo;

	public UserInfoText(Map<String, String> userInfo) {
		this.userInfo = userInfo;
	}

	public String buildTextResponse() {
		StringBuilder textResponse = new StringBuilder();

		String title = getUserTitle();
		String name = getUserName();
		String surname = getUserSurname();

		textResponse.append("Hello! ")
			.append(String.format("<div> %s %s %s</div></br>", title, name, surname))
			.append("<div>How are you today???</div>");

		return textResponse.toString();
	}

	public String getUserSurname() {
		return userInfo.get("user_surname");
	}

	public String getUserName() {
		return userInfo.get("user_name") != null ? userInfo.get("user_name") : userInfo.get("email");
	}

	public String getUserTitle() {
		if (!userInfo.containsKey("gender") || userInfo.get("gender") == null) {
			return " ";
		}

		if (userInfo.get("gender").equals("female")) {
			return "Miss";
		} else if (userInfo.get("gender").equals("male")) {
			return "Mr.";
		}
		return "";
	}
}