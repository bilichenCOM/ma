package initializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import db.GoodDao;
import db.UserDao;
import db.impl.GoodDaoImpl;
import db.impl.UserDaoImpl;
import model.Book;
import model.User;
import utils.ShaPasswordGenerator;

@WebServlet(value = "/init", loadOnStartup = 1)
public class InitializerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final UserDao userDao = new UserDaoImpl();
	private static final GoodDao goodDao = new GoodDaoImpl();

	@Override
	public void init() throws ServletException {
		String name = "Johny";
		String surname = "Hurricane";
		Integer age = 20;
		String gender = "male";
		String email = "hurricane@mail.com";
		String password = "0000";
		Integer roleId = 2;
		Double balance = 5000.0;
		String salt = ShaPasswordGenerator.generateSalt();
		password = ShaPasswordGenerator.getSha256Password(password, salt);

		User user = new User(name, surname, gender, age, email, password, roleId, balance, salt);
		userDao.add(user);

		String name2 = "Jack";
		String surname2 = "Sparrow";
		Integer age2 = 20;
		String gender2 = "male";
		String email2 = "sparrow@mail.com";
		String password2 = "0000";
		Integer roleId2 = 2;
		Double balance2 = 5000.0;
		String salt2 = ShaPasswordGenerator.generateSalt();
		password2 = ShaPasswordGenerator.getSha256Password(password2, salt2);

		User user2 = new User(name2, surname2, gender2, age2, email2, password2, roleId2, balance2, salt2);
		userDao.add(user2);

		String adminName = "admin";
		String adminPassword = "admin";
		Integer adminAge = 30;
		String adminEmail = "admin@mail.com";
		Integer adminRoleId = 1;
		String adminSalt = ShaPasswordGenerator.generateSalt();
		adminPassword = ShaPasswordGenerator.getSha256Password(adminPassword, adminSalt);

		User admin = new User(adminName, null, null, adminAge, adminEmail, adminPassword, adminRoleId, null, adminSalt);
		userDao.add(admin);

		String title = "How to make chocolate cake";
		String author = "Dr. Cook";
		Integer pages = 200;
		Integer year = 2019;
		String imageUrl = "http://space.com/favicon.ico";
		Double price = 322.0;

		Book book = new Book(title, author, year, pages, imageUrl, price);
		goodDao.add(book);

		String title2 = "5 ways to find your chocolate";
		String author2 = "Dr. Cook";
		Integer pages2 = 200;
		Integer year2 = 2019;
		String imageUrl2 = "http://spacex.com/favicon.ico";
		Double price2 = 322.0;

		Book book2 = new Book(title2, author2, year2, pages2, imageUrl2, price2);
		goodDao.add(book2);

	}
}
