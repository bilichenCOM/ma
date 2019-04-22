package com;
import java.util.HashMap;
import java.util.Map;

public class UserDataBase {
	private final Map<String, Map<String, String>> userInfo;
	private static final UserDataBase instance = new UserDataBase();

	private UserDataBase() {
		userInfo = new HashMap<>();
	}

	public boolean addUniqueUser(String email, Map<String, String> info) {
		System.out.println("adding new user....");
		if (userInfo.containsKey(email)) {
			System.out.println("user " + email + " already exist! info not added");
			return false;
		}
		userInfo.put(email, info);
		System.out.println("user " + email + " added!");
		return true;
	}

	public Map<String, String> getUserInfo(String email, String passwd) {
		if(checkCredentials(email, passwd)) {
			
			return userInfo.get(email);
		}
		return new HashMap<>();
	}

	public boolean checkCredentials(String email, String passwd) {
		System.out.println("checking credentials....");
		if (userInfo.containsKey(email) && userInfo.get(email).get("passwd").equals(passwd)) {
			System.out.println("success!");
			return true;
		}
		System.out.println("wrong credentials!");
		return false;
	}

	public static UserDataBase getInstance() {
		return instance;
	}
}