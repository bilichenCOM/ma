package db;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.User;

public class UserDao implements CabinetCrud<User> {

	private static SessionFactory factory = HibernateUtil.getSessionFactory();

	public UserDao() {
	}

	@Override
	public void add(User user) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
		session.close();
	}

	@Override
	public Optional<User> read(Long id) {
		Session session = factory.openSession();
		User user = session.find(User.class, id);
		session.close();
		return Optional.of(user);
	}

	public Optional<User> read(String email) {
		Session session = factory.openSession();
		User user = session.createQuery("SELECT u FROM User u WHERE u.email = '" + email + "'"
				, User.class).getSingleResult();
		session.close();
		return Optional.of(user);
	}

	@Override
	public void update(User user) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();
	}

	@Override
	public void delete(Long id) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		User user = session.get(User.class, id);
		session.delete(user);
		tx.commit();
		session.close();
	}

	@Override
	public List<User> readAll() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String queryString = "SELECT u FROM User u";
		@SuppressWarnings("unchecked")
		List<User> userList = session.createQuery(queryString).list();
		tx.commit();
		session.close();
		return userList;
	}
}