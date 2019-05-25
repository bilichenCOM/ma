package db.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import db.UserDao;
import model.User;
import utils.HibernateUtil;

public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	private static final SessionFactory factory = HibernateUtil.getSessionFactory();
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

	public UserDaoImpl() {
	}
	
	public Optional<User> read(Long id) {
		return read(User.class, id);
	}

	public Optional<User> readByEmail(String email) {
		Session session = factory.openSession();
		Query<User> query = session.createQuery("SELECT u FROM User u WHERE u.email = '" + email + "'"
				, User.class);
		logger.debug(String.format("reading user with email %s from database...", email));
		User user = query.getSingleResult();
		session.close();
		return Optional.ofNullable(user);
	}

	public void delete(Long id) {
		delete(User.class, id);
	}

	public List<User> readAll() {
		return readAll(User.class);
	}
}