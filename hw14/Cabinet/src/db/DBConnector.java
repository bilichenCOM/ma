package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;

import model.User;

public class DBConnector {
	private static final String DRIVER_NAME = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/cabinet_db";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "admin";
	
	private static final Logger logger = Logger.getLogger(DBConnector.class);

	private static Connection connection;

	static {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			logger.error("driver to db not found");
		}
	}

	private DBConnector() {
	}

	public static void addUser(User user) throws ExistingUserException {
		String sql = "INSERT INTO users(name, surname, age, gender, email, password, role_id) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement addingQuery = connection.prepareStatement(sql);
			addingQuery.setString(1, user.getName());
			addingQuery.setString(2, user.getSurname());
			addingQuery.setInt(3, user.getAge());
			addingQuery.setString(4, user.getGender());
			addingQuery.setString(5, user.getEmail());
			addingQuery.setString(6, user.getPassword());
			addingQuery.setInt(7, user.getRoleId());
			addingQuery.execute();
		} catch (SQLException e) {
			logger.debug("sql exception", e);
			throw new ExistingUserException();
		}
	}

	public static Optional<User> getUser(String email) throws WrongEmailException {
		System.out.println("getting user from database....");
		String sql = "SELECT * FROM users WHERE email=?";

		try {
			PreparedStatement getUserQuery = connection.prepareStatement(sql);

			getUserQuery.setString(1, email);
			ResultSet getUserResultSet = getUserQuery.executeQuery();

			if (getUserResultSet.next()) {
				Long id = getUserResultSet.getLong("id");
				String name = getUserResultSet.getString("name");
				String surname = getUserResultSet.getString("surname");
				int age = getUserResultSet.getInt("age");
				String gender = getUserResultSet.getString("gender");
				String password = getUserResultSet.getString("password");
				int roleId = getUserResultSet.getInt("role_id");
				return Optional.of(new User(id, name, surname, gender, age, email, password, roleId));
			} else {
				throw new WrongEmailException();
			}

		} catch (SQLException e) {
			logger.debug("sql exception", e);
		}
		return Optional.empty();
	}

	public static void updateUser(User user) {
		String sql = "UPDATE users "
				+ "SET name = ?, surname = ?, age = ?, gender = ?, password = ?, role_id = ? "
				+ "WHERE email ='" + user.getEmail() + "'";

		try {
			PreparedStatement updateQuery = connection.prepareStatement(sql);

			updateQuery.setString(1, user.getName());
			updateQuery.setString(2, user.getSurname());
			updateQuery.setInt(3, user.getAge());
			updateQuery.setString(4, user.getGender());
			updateQuery.setString(5, user.getPassword());
			updateQuery.setInt(6, user.getRoleId());
			updateQuery.execute();
		} catch (SQLException e) {
			logger.debug("problems by updating user", e);
		}
	}

	public static void deleteUser(String email) {
		System.out.println("deleting users....");
		 try {
			Statement deleteSql = connection.createStatement();
			deleteSql.execute("DELETE FROM public.users "
							+ "WHERE email='" + email + "';");
		} catch (SQLException e) {
			logger.debug("problems by deleting users");
		}
	}

	public static Map<String, String> getUserInfo(String email, String passwd) throws WrongEmailException {
		Map<String, String> userInfo = new HashMap<>();
		try {
			Statement selectSql = connection.createStatement();
			ResultSet rs = selectSql.executeQuery("SELECT * FROM public.users "
					+ "WHERE email='" + email + "' AND passwd='" + passwd + "';");
			userInfo = makeUserInfoMap(rs);
			if (userInfo.get("email") == null) {
				logger.debug("wrong credentials! " + email + " " + passwd);
				throw new WrongEmailException();
			}
		} catch (SQLException e) {
			logger.debug("problems by getting user info");
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
			logger.debug("crashed by parsing user data");
		}
		return userInfo;
	}

	public static void connect() throws ConnectionException {
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			logger.error("connection to database failed");
			throw new ConnectionException();
		}
	}

	public static void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			logger.debug("connection closing failed");
		}
	}
}