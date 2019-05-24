package db.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import db.UserDao;
import model.User;
import utils.HibernateUtil;

public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	private static SessionFactory factory = HibernateUtil.getSessionFactory();

	public UserDaoImpl() {
	}
	
	public Optional<User> read(Long id) {
		return read(User.class, id);
	}

	public Optional<User> readByEmail(String email) {
		Session session = factory.openSession();
		Query<User> query = session.createQuery("SELECT u FROM User u WHERE u.email = '" + email + "'"
				, User.class);
		if (query.list().size() == 0) {
			session.close();
			return Optional.empty();
		}
		User user = query.getSingleResult();
		session.close();
		return Optional.of(user);
	}

	public void delete(Long id) {
		delete(User.class, id);
	}

	public List<User> readAll() {
		return readAll(User.class);
	}
}