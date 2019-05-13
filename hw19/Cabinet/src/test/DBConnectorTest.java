package test;

import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import db.ConnectionException;
import db.DbConnector;
import db.ExistingUserException;
import db.WrongEmailException;
import model.User;

class DBConnectorTest {

	@BeforeAll
	static void openConnection() throws ConnectionException {
		DbConnector.connect();
	}
	
	@AfterAll
	static void closeConnection() {
		DbConnector.disconnect();
	}
	
	@Test
	void testAddUser() throws ExistingUserException {
		User test = new User("Pipka", "Pitek", "male", 19, "pp@mail.com", "0000", 1, 0.0);
		Assertions.assertThrows(ExistingUserException.class, () -> DbConnector.addUser(test));	
	}

	@Test
	void testGetUserInfo() throws WrongEmailException, ExistingUserException {
		Map<String, String> userInfo = DbConnector.getUserInfo("realCat@mail.com", "iLoveMouses1234");
		Assertions.assertEquals("Cat", userInfo.get("user_name"));
		Assertions.assertThrows(WrongEmailException.class, () -> DbConnector.getUserInfo("realCat@mail.com", "0000"));
	}

	@Test
	void testUpdateUserInfo() throws WrongEmailException {
		User test = new User("Pipka", "Pitek", "male", 19, "pp@mail.com", "0000", 1, 0.0);
		test.setAge(29);
		test.setName("Johny");
		DbConnector.updateUser(test);
	}

	@Test
	void testDeleteUser() throws ExistingUserException, WrongEmailException {
		String testEmail = "temporaryCat@mail.com";
		String testPasswd = "icePass";
		User test = new User("Pipka", "Pitek", "male", 19, "pp@mail.com", "0000", 1, 0.0);
		DbConnector.addUser(test);
		Assertions.assertEquals("Catty", DbConnector.getUserInfo(testEmail, testPasswd).get("user_name"));
		DbConnector.deleteUser(test.getEmail());
		Assertions.assertThrows(WrongEmailException.class, () -> DbConnector.getUserInfo(testEmail, testPasswd));
	}
}