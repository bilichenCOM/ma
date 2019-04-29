package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import model.User;

public class DBConnector {
	private static final String DRIVER_NAME = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/cabinet_db";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "admin";

	private static Connection connection;

	static {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.err.println("#ERROR: Driver to database not found");
		}
	}

	private DBConnector() {
	}

	public static void addUser(User user) throws ExistingUserException {
		System.out.println("adding new user...");
		try {
			Statement checkSql = connection.createStatement();
			ResultSet res = checkSql.executeQuery("SELECT * FROM public.users WHERE email='" + user.getEmail() + "';");
			if (res.next() && res.getString(1).equals(user.getEmail())) {
				throw new ExistingUserException();
			}
		} catch (SQLException e1) {
			System.err.println(e1.getMessage());
		}

		try {
			PreparedStatement preparedSql = connection.prepareStatement("INSERT INTO public.users(email, user_name, user_surname, user_age, passwd, user_gender)"
										+ "VALUES (?, ?, ?, ?, ?, ?);");
			preparedSql.setString(1, user.getEmail());
			preparedSql.setString(2, user.getName());
			preparedSql.setString(3, user.getSurname());
			preparedSql.setInt(4, user.getAge());
			preparedSql.setString(5, user.getPasswd());
			preparedSql.setString(6, user.getGender());
			preparedSql.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("done!");
	}

	public static User getUser(String email, String passwd) throws WrongCredentialsException {
		System.out.println("getting user from database....");
		return new User(getUserInfo(email, passwd));
	}

	public static void updateUser(User user) {
		System.out.println("updating user...");
		System.out.println("deleting old user....");
		deleteUser(user.getEmail());
		try {
			addUser(user);
		} catch (ExistingUserException e) {}
	}

	public static void deleteUser(User user) {
		System.out.println("deleting users....");
		 try {
			Statement deleteSql = connection.createStatement();
			deleteSql.execute("DELETE FROM public.users "
							+ "WHERE email='" + user.getEmail() + "';");
		} catch (SQLException e) {
			System.err.println(user.getEmail() + " user not deleted!");
		}
	}

	public static void deleteUser(String email) {
		deleteUser(new User(email, null));
	}

	public static Map<String, String> getUserInfo(String email, String passwd) throws WrongCredentialsException {
		Map<String, String> userInfo = new HashMap<>();
		try {
			Statement selectSql = connection.createStatement();
			ResultSet rs = selectSql.executeQuery("SELECT * FROM public.users "
					+ "WHERE email='" + email + "' AND passwd='" + passwd + "';");
			userInfo = makeUserInfoMap(rs);
			if (userInfo.get("email") == null) {
				throw new WrongCredentialsException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	private static Map<String, String> makeUserInfoMap(ResultSet rs) {
		Map<String, String> userInfo = new HashMap<>();
		try {
			if (rs.next()) {
				userInfo.put("user_name", rs.getString("user_name"));
				userInfo.put("user_surname", rs.getString("user_surname"));
				userInfo.put("user_age", rs.getString("user_age"));
				userInfo.put("email", rs.getString("email"));
				userInfo.put("passwd", rs.getString("passwd"));
				userInfo.put("user_gender", rs.getString("user_gender"));
			}
		} catch (SQLException e) {
			System.err.println("error by retrieving user info!");
		}
		return userInfo;
	}

	public static void connect() throws ConnectionException {
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			throw new ConnectionException();
		}
	}

	public static void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Error by closing connection");
		}
	}
}