package initializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import db.GoodDao;
import db.UserDao;
import db.impl.GoodDaoImpl;
import db.impl.UserDaoImpl;
import model.Book;
import model.Protein;
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
		String imgUrl2 = "http://spacex.com/favicon.ico";
		Double price2 = 322.0;

		Book book2 = new Book(title2, author2, year2, pages2, imgUrl2, price2);
		goodDao.add(book2);

		String imageUrl1 = "https://images-na.ssl-images-amazon.com/images/I/71TVOmJQ2QL._SX466_.jpg";
		String imageUrl2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTo_pdKdlWmPIfRu9H_y8Atmk_Ko_B_7YLy5OHz9PMgcwzHJWI1";
		String imageUrl3 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaQN8ZnLEEA1bPIAKYo8EnmNF9mjMBKx6wqXCXdTPhpAPzaTJb";
		String imageUrl4 = "https://i1.rozetka.ua/goods/371695/sn_100_whey_protein_prof_920_g_banana_images_371695382.jpg";
		String imageUrl5 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfq2ZAM57r-hBQzi_FuxC6ZL0sDFq85cM1OtTiMlAaANpSl7uR";
		String imageUrl6 = "https://i0.wp.com/www.healthline.com/hlcmsresource/images/AN_images/whey-protein-1296x728-feature.jpg?w=1155&h=1528";

		goodDao.add(new Protein("Good Muscle", "Whey Company", 2019, imageUrl1, 500D));
		goodDao.add(new Protein("Good Biceps", "Whey Company", 2019, imageUrl2, 500D));
		goodDao.add(new Protein("Good Triceps", "Whey Company", 2019, imageUrl3, 500D));
		goodDao.add(new Protein("Good Legs", "Whey Company", 2019, imageUrl4, 500D));
		goodDao.add(new Protein("Good Wings", "Whey Company", 2019, imageUrl5, 500D));
		goodDao.add(new Protein("Good All in", "Whey Company", 2019, imageUrl6, 1000D));

	}
}
