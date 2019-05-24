package db.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import db.PurchaseDao;
import model.Purchase;
import model.PurchaseStatus;
import utils.HibernateUtil;

public class PurchaseDaoImpl extends GenericDaoImpl<Purchase> implements PurchaseDao {

	private static final SessionFactory factory = HibernateUtil.getSessionFactory();
	
	@Override
	public Optional<Purchase> read(Long id) {
		return read(Purchase.class, id);
	}

	@Override
	public List<Purchase> readAll() {
		return readAll(Purchase.class);
	}

	@Override
	public void delete(Long id) {
		delete(Purchase.class, id);
	}

	@Override
	public List<Purchase> getBasketPurchases(Long userId) {
		Session session = factory.openSession();
		session.beginTransaction();
		Query<Purchase> query = session.createQuery("FROM Purchase p "
				+ "WHERE p.userId = :userId AND p.statusId = :statusId", Purchase.class);
		query.setParameter("userId", userId);
		query.setParameter("statusId", PurchaseStatus.UNFINISHED.getId());
		List<Purchase> purchases = query.list();
		return purchases;
	}
}