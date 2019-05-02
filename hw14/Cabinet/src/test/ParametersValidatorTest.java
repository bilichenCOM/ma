package test;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import utils.ParametersValidator;

class ParametersValidatorTest {

	@Test
	void testCheckSignUpInfo() {
		Map<String, String> info = new HashMap<>();
		info.put("user_name", "DJWW");
		info.put("user_surname", "H9879jkjhurricane");
		info.put("user_age", "1000");
		info.put("passwd", "0sdkf0dKHK");
		info.put("email", "amazon.com@mail.com");
		Assertions.assertTrue(ParametersValidator.checkSignUpInfo(info));
	}
}
