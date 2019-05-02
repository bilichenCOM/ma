package test;

import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ConnectionException;
import com.DBConnector;
import com.ExistingUserException;
import com.WrongEmailException;

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
		User test = new User("Pipka", "Pitek", "male", 19, "pp@mail.com", "0000", 1);
		Assertions.assertThrows(ExistingUserException.class, () -> DBConnector.addUser(test));	
	}

	@Test
	void testGetUserInfo() throws WrongEmailException, ExistingUserException {
		Map<String, String> userInfo = DBConnector.getUserInfo("realCat@mail.com", "iLoveMouses1234");
		Assertions.assertEquals("Cat", userInfo.get("user_name"));
		Assertions.assertThrows(WrongEmailException.class, () -> DBConnector.getUserInfo("realCat@mail.com", "0000"));
	}

	@Test
	void testUpdateUserInfo() throws WrongEmailException {
		User test = new User("Pipka", "Pitek", "male", 19, "pp@mail.com", "0000", 1);
		test.setAge(29);
		test.setName("Johny");
		DBConnector.updateUser(test);
	}

	@Test
	void testDeleteUser() throws ExistingUserException, WrongEmailException {
		String testEmail = "temporaryCat@mail.com";
		String testPasswd = "icePass";
		User test = new User("Pipka", "Pitek", "male", 19, "pp@mail.com", "0000", 1);
		DBConnector.addUser(test);
		Assertions.assertEquals("Catty", DBConnector.getUserInfo(testEmail, testPasswd).get("user_name"));
		DBConnector.deleteUser(test.getEmail());
		Assertions.assertThrows(WrongEmailException.class, () -> DBConnector.getUserInfo(testEmail, testPasswd));
	}
}