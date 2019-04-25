package com;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ParametersValidator {

	public static Map<String, String> formatParamMap(Map<String, String[]> params) {
		Map<String, String> formatParams = new HashMap<>();
		params.keySet().stream()
			.forEach(k -> formatParams.put(k, params.get(k)[0]));
		return formatParams;
	}

	public static boolean checkSignUpInfo(Map<String, String> info) {
		return checkEmailValidity(info.get("email")) && checkTextValidity(info.get("user_name"))
				&& checkTextValidity(info.get("user_surname")) && checkTextValidity(info.get("passwd"))
				&& checkNumberValidity(info.get("user_age"));
	}

	public static boolean checkNumberValidity(String number) {
		return number != null ? Pattern.matches("\\d+", number) : false;
	}

	public static boolean checkTextValidity(String text) {
		return text != null ? Pattern.matches("[a-zA-Zа-яА-Я_0-9]+", text) : false;
	}

	public static boolean checkEmailValidity(String email) {
		return email != null ? Pattern.matches("\\w+\\.?\\w+@\\w+\\.\\w+", email) : false;
	}
}