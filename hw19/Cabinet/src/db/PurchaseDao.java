package db;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Purchase;

public class PurchaseDao implements CabinetCrud<Purchase>{

	private static final SessionFactory FACTORY = HibernateUtil.getSessionFactory();

	@Override
	public void add(Purchase purchase) {
		Session session = FACTORY.openSession();
		session.beginTransaction();
		session.save(purchase);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Optional<Purchase> read(Long id) {
		Session session = FACTORY.openSession();
		Purchase purchase = session.get(Purchase.class, id);
		session.close();
		return Optional.of(purchase);
	}

	@Override
	public void update(Purchase t) {
		Session session = FACTORY.openSession();
		session.beginTransaction();
		session.update(t);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(Long id) {
		Session session = FACTORY.openSession();
		session.beginTransaction();
		Purchase purchase = session.get(Purchase.class, id);
		session.delete(purchase);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Purchase> readAll() {
		Session session = FACTORY.openSession();
		session.beginTransaction();
		String queryString = "SELECT p FROM Purchase p";
		@SuppressWarnings("unchecked")
		List<Purchase> purchases = session.createQuery(queryString).list();
		session.getTransaction().commit();
		session.close();
		return purchases;
	}
}