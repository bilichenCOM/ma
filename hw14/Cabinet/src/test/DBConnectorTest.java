package test;

import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ConnectionException;
import com.DBConnector;
import com.ExistingUserException;
import com.WrongCredentialsException;

import model.User;

class DBConnectorTest {

	@BeforeAll
	static void openConnection() throws ConnectionException {
		DBConnector.connect();
	}
	
	@AfterAll
	static void closeConnection() {
		DBConnector.disconnect();
	}
	
	@Test
	void testAddUser() throws ExistingUserException {
		User test = new User("pipka@mail.com", "0000");
		test.setName("Pipka");
		test.setSurname("Pitek");
		test.setAge(30);
		Assertions.assertThrows(ExistingUserException.class, () -> DBConnector.addUser(test));	
	}

	@Test
	void testGetUserInfo() throws WrongCredentialsException, ExistingUserException {
		Map<String, String> userInfo = DBConnector.getUserInfo("realCat@mail.com", "iLoveMouses1234");
		Assertions.assertEquals("Cat", userInfo.get("user_name"));
		Assertions.assertThrows(WrongCredentialsException.class, () -> DBConnector.getUserInfo("realCat@mail.com", "0000"));
	}

	@Test
	void testUpdateUserInfo() throws WrongCredentialsException {
		User test = new User("pipka@mail.com", "0000");
		test.setAge(29);
		test.setName("Johny");
		DBConnector.updateUser(test);
	}

	@Test
	void testDeleteUser() throws ExistingUserException, WrongCredentialsException {
		String testEmail = "temporaryCat@mail.com";
		String testPasswd = "icePass";
		User test = new User(testEmail, testPasswd);
		test.setName("Catty");
		DBConnector.addUser(test);
		Assertions.assertEquals("Catty", DBConnector.getUserInfo(testEmail, testPasswd).get("user_name"));
		DBConnector.deleteUser(test);
		Assertions.assertThrows(WrongCredentialsException.class, () -> DBConnector.getUserInfo(testEmail, testPasswd));
	}
}