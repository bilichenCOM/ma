package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;

import model.Book;
import model.Purchase;
import model.User;

public class DbConnector {
	private static final String DRIVER_NAME = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/cabinet_db";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "admin";
	
	private static final Logger logger = Logger.getLogger(DbConnector.class);

	private static Connection connection;

	static {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			logger.error("driver to db not found");
		}
	}

	private DbConnector() {
	}

	public static int addPurchase(Purchase purchase) {
		String sql = "INSERT INTO purchases(book_id, user_id, value) "
				+ "VALUES (?, ?, ?)";
		try {
			PreparedStatement addingQuery = connection.prepareStatement(sql);
			addingQuery.setLong(1, purchase.getBookId());
			addingQuery.setLong(2, purchase.getUserId());
			addingQuery.setDouble(3, purchase.getValue());
			return addingQuery.executeUpdate();
		} catch (SQLException e) {
			logger.debug("problems by adding purchase to db", e);
		}
		return 0;
	}

	public static Optional<Purchase> getPurchase(Long id) {
		String sql = "SELECT * FROM purchases WHERE id = '" + id + "'";
		try {
			Statement selectQuery = connection.createStatement();
			ResultSet selectQueryResultSet = selectQuery.executeQuery(sql);
			if (selectQueryResultSet.next()) {
				Long bookId = selectQueryResultSet.getLong("book_id");
				Long userId = selectQueryResultSet.getLong("user_id");
				Double value = selectQueryResultSet.getDouble("value");
				Purchase purchase = new Purchase(bookId, userId, value);
				return Optional.of(purchase);
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			logger.debug("purchase not found in db", e);
		}
		return Optional.empty();
	}

	public static void addBook(Book book) {
		String sql = "INSERT INTO books(title, author, year, pages, imageurl) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement addingQuery = connection.prepareStatement(sql);
			addingQuery.setString(1, book.getTitle());
			addingQuery.setString(2, book.getAuthor());
			addingQuery.setInt(3, book.getYear());
			addingQuery.setInt(4, book.getPages());
			addingQuery.setString(5, book.getImageUrl());
			addingQuery.execute();
		} catch (SQLException e) {
			logger.debug("problems by adding new books", e);
		}
	}

	public static Optional<Book> getBook(Long id) {
		String sql = "SELECT * FROM books WHERE id = " + id;
		try {
			Statement selectQuery = connection.createStatement();
			ResultSet selectBookResultSet = selectQuery.executeQuery(sql);
			if (selectBookResultSet.next()) {
				String title = selectBookResultSet.getString("title");
				String author = selectBookResultSet.getString("author");
				int year = selectBookResultSet.getInt("year");
				int pages = selectBookResultSet.getInt("pages");
				String imageUrl = selectBookResultSet.getString("image_url");
				int price = selectBookResultSet.getInt("price");

				Book book = new Book(id, title, author, year, pages, imageUrl, price);
				return Optional.of(book);
			}
		} catch (SQLException e) {
			logger.debug("problems by getting book", e);
		}
		return Optional.empty();
	}

	public static List<Book> getBookList() {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM books";
		try {
			Statement selectQuery = connection.createStatement();
			ResultSet selectBooksResultSet = selectQuery.executeQuery(sql);
			
			while(selectBooksResultSet.next()) {
				Long id = selectBooksResultSet.getLong("id");
				String title = selectBooksResultSet.getString("title");
				String author = selectBooksResultSet.getString("author");
				int year = selectBooksResultSet.getInt("year");
				int pages = selectBooksResultSet.getInt("pages");
				String imageUrl = selectBooksResultSet.getString("imageurl");
				int price = selectBooksResultSet.getInt("price");
				
				Book book = new Book(id, title, author, year, pages, imageUrl, price);
				books.add(book);
			}
		} catch (SQLException e) {
			logger.debug("problems by getting book list");
		}
		return books;
	}

	public static void updateBook(Book book) {
		Long id = book.getId();
		String title = book.getTitle();
		String author = book.getAuthor();
		int year = book.getYear();
		int pages = book.getPages();
		String imageUrl = book.getImageUrl();

		String sql = "UPDATE books SET title = ?, author = ?, year = ?, pages = ?, image_url = ?, price = ? WHERE id = ?";
		try {
			PreparedStatement updateQuery = connection.prepareStatement(sql);
			updateQuery.setString(1, title);
			updateQuery.setString(2, author);
			updateQuery.setInt(3, year);
			updateQuery.setInt(4, pages);
			updateQuery.setString(5, imageUrl);
			updateQuery.setLong(6, id);
			updateQuery.execute();
		} catch (SQLException e) {
			logger.debug("problems by updating book", e);
		}
	}
	
	public static void deleteBook(Long id) {
		String sql = "DELETE FROM books WHERE id = ?";
		try {
			PreparedStatement deleteQuery = connection.prepareStatement(sql);
			deleteQuery.setLong(1, id);
			deleteQuery.execute();
		} catch (SQLException e) {
			logger.debug("problems by deleting book ", e);
		}
	}
	public static void addUser(User user) {
		String sql = "INSERT INTO users(name, surname, age, gender, email, password, role_id, balance, salt) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement addingQuery = connection.prepareStatement(sql);
			addingQuery.setString(1, user.getName());
			addingQuery.setString(2, user.getSurname());
			addingQuery.setInt(3, user.getAge());
			addingQuery.setString(4, user.getGender());
			addingQuery.setString(5, user.getEmail());
			addingQuery.setString(6, user.getPassword());
			addingQuery.setInt(7, user.getRoleId());
			addingQuery.setDouble(8, user.getBalance());
			addingQuery.setString(9, user.getSalt());
			addingQuery.execute();
		} catch (SQLException e) {
			logger.debug("sql exception", e);
			throw new ExistingUserException();
		}
	}
	
	public static Optional<User> getUser(Long id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		try {
			PreparedStatement getUserQuery = connection.prepareStatement(sql);
			getUserQuery.setLong(1, id);
			
			ResultSet getUserResultSet = getUserQuery.executeQuery();
			if (getUserResultSet.next()) {
				String name = getUserResultSet.getString("name");
				String surname = getUserResultSet.getString("surname");
				int age = getUserResultSet.getInt("age");
				String gender = getUserResultSet.getString("gender");
				String email = getUserResultSet.getString("email");
				String password = getUserResultSet.getString("password");
				int roleId = getUserResultSet.getInt("role_id");
				int balance = getUserResultSet.getInt("balance");
				String salt = getUserResultSet.getString("salt");
				
				return Optional.of(new User(id, name, surname, gender, age, email, password, roleId, balance, salt));
			} else {
				throw new WrongEmailException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public static Optional<User> getUser(String email) {
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
				int balance = getUserResultSet.getInt("balance");
				String salt = getUserResultSet.getString("salt");

				return Optional.of(new User(id, name, surname, gender, age, email, password, roleId, balance, salt));
			} else {
				throw new WrongEmailException();
			}

		} catch (SQLException e) {
			logger.debug("sql exception", e);
		}
		return Optional.empty();
	}

	public static List<User> getUserList() {
		List<User> userList = new ArrayList<>();
		String sql = "SELECT * FROM users";
		try {
			Statement selectQuery = connection.createStatement();
			ResultSet selectQueryResultSet = selectQuery.executeQuery(sql);
			while (selectQueryResultSet.next()) {
				Long id = selectQueryResultSet.getLong("id");
				String name = selectQueryResultSet.getString("name");
				String surname = selectQueryResultSet.getString("surname");
				int age = selectQueryResultSet.getInt("age");
				String gender = selectQueryResultSet.getString("gender");
				String email = selectQueryResultSet.getString("email");
				String password = selectQueryResultSet.getString("password");
				int roleId = selectQueryResultSet.getInt("role_id");
				int balance = selectQueryResultSet.getInt("balance");
				String salt = selectQueryResultSet.getString("salt");

				User user = new User(id, name, surname, gender, age, email, password, roleId, balance, salt);
				userList.add(user);
			}
		} catch (SQLException e) {
			logger.debug("problems by fetching user list from db", e);
		}
		return userList;
	}
	public static void updateUser(User user) {
		String sql = "UPDATE users "
				+ "SET name = ?, surname = ?, age = ?, gender = ?, password = ?, role_id = ?, balance = ? "
				+ "WHERE id ='" + user.getId() + "'";

		try {
			PreparedStatement updateQuery = connection.prepareStatement(sql);

			updateQuery.setString(1, user.getName());
			updateQuery.setString(2, user.getSurname());
			updateQuery.setInt(3, user.getAge());
			updateQuery.setString(4, user.getGender());
			updateQuery.setString(5, user.getPassword());
			updateQuery.setInt(6, user.getRoleId());
			updateQuery.setDouble(7, user.getBalance());
			updateQuery.execute();
		} catch (SQLException e) {
			logger.debug("problems by updating user", e);
		}
	}

	public static void deleteUser(Long id) {
		String sql = "DELETE FROM users WHERE id = '" + id + "'";
		try {
			Statement deleteQuery = connection.createStatement();
			deleteQuery.execute(sql);
			logger.debug("user with id " + id + " deleted");
		} catch (SQLException e) {
			logger.debug("problems by deleting users", e);
		}
	}
	
	public static void deleteUser(String email) {
		 try {
			Statement deleteSql = connection.createStatement();
			deleteSql.execute("DELETE FROM public.users "
							+ "WHERE email='" + email + "';");
			logger.debug("user with email " + email + " deleted");
		} catch (SQLException e) {
			logger.debug("problems by deleting users", e);
		}
	}

	public static Map<String, String> getUserInfo(String email, String passwd) {
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

	public static void connect() {
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