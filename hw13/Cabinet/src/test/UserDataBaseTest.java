package test;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.UserDataBase;

class UserDataBaseTest {
	private static UserDataBase testUserDataBase;

	@BeforeAll
	static void setUpBeforeClass() {
		testUserDataBase = UserDataBase.getInstance();
	}

	@Test
	void testAddUniqueUser() {
		String email = "good@mail.com";
		Map<String, String> userInfo = new HashMap<>();
		testUserDataBase.addUniqueUser(email, userInfo);
		Assertions.assertFalse(testUserDataBase.addUniqueUser(email, userInfo));
	}

	@Test
	void testGetUserInfo() {
		String email = "johny@mail.com";
		String passwd = "sercretPass";
		String name = "Johny";
		Map<String, String> info = new HashMap<>();
		info.put("passwd", passwd);
		info.put("name", name);
		testUserDataBase.addUniqueUser(email, info);
		Assertions.assertEquals(name, testUserDataBase.getUserInfo(email, passwd).get("name"));
		Assertions.assertFalse(testUserDataBase.getUserInfo(email, "badPass").containsKey("name"));
	}

	@Test
	void testCheckCredentials() {
		String email = "realCat@mail.com";
		String passwd = "iLoveMouses1234";
		Map<String, String> info = new HashMap<>();
		info.put("passwd", passwd);
		testUserDataBase.addUniqueUser(email, info);
		Assertions.assertTrue(testUserDataBase.checkCredentials(email, passwd));
		Assertions.assertFalse(testUserDataBase.checkCredentials(email, "iLoveCheese1234"));
		Assertions.assertFalse(testUserDataBase.checkCredentials("badCat@mail.com", passwd));
		Assertions.assertFalse(testUserDataBase.checkCredentials("coolCat@mail.com", "iLoveDrugs5553535"));
	}
}