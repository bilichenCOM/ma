package db.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import db.PurchaseDao;
import model.Purchase;
import model.PurchaseStatus;
import utils.HibernateUtil;

public class PurchaseDaoImpl extends GenericDaoImpl<Purchase> implements PurchaseDao {

	private static final SessionFactory factory = HibernateUtil.getSessionFactory();
	private static final Logger logger = Logger.getLogger(PurchaseDaoImpl.class);

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
		Transaction transaction = null;
		List<Purchase> purchases = new ArrayList<>();

		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			Query<Purchase> query = session.createQuery("FROM Purchase p "
					+ "WHERE p.userId = :userId AND p.statusId = :statusId", Purchase.class);
			query.setParameter("userId", userId);
			query.setParameter("statusId", PurchaseStatus.UNFINISHED.getId());
			logger.debug(String.format("getting unfinished purchases for user with id %d", userId));
			purchases = query.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.debug(String.format("problems by getting unfinished purchases "
					+ "for user with id %s from database...", userId));
		}
		return purchases;
	}
}