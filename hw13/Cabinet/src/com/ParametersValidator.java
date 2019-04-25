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

	public static boolean isValidCredentials(String email, String passwd) {
		return Pattern.matches("\\w+@\\w+\\.\\w+", email) && Pattern.matches("\\w+", passwd);
	}
}