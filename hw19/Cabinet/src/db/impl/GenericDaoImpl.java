package db.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import db.GenericDao;
import utils.HibernateUtil;

public abstract class GenericDaoImpl<T> implements GenericDao<T>{

	private static final SessionFactory factory = HibernateUtil.getSessionFactory();
	private static final Logger logger = Logger.getLogger(GenericDaoImpl.class);

	@Override
	public void add(T t) {
		Transaction tx = null;

		try (Session session = factory.openSession()) {
			tx = session.beginTransaction();
			logger.debug(String.format("saving %s instance to database...", t.getClass().getTypeName()));
			session.save(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.debug(String.format("problems by adding new %s instances to database...", t.getClass().getTypeName()));
		}
	}

	@Override
	public  Optional<T> read(Class<T> clazz, Long id) {
		Transaction tx = null;
		T t = null;

		try (Session session = factory.openSession()) {
			tx = session.beginTransaction();
			logger.debug(String.format("reading %s with id %d from database", clazz.getTypeName(), id));
			t = session.get(clazz, id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.debug(String.format("problems by reading %s instance with id %d from database...", clazz.getTypeName(), id));
		}
		return Optional.ofNullable(t);
	}

	@Override
	public void update(T t) {
		Transaction tx = null;

		try (Session session = factory.openSession()) {
			tx = session.beginTransaction();
			logger.debug(String.format("updating %s instance...", t.getClass().getTypeName()));
			session.update(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.debug(String.format("problems by updating %s instance of ", t.getClass().getTypeName()));
		}
	}

	@Override
	public void delete(Class<T> clazz, Long id) {
		Transaction tx = null;

		try (Session session = factory.openSession()) {
			tx = session.beginTransaction();
			T t = session.get(clazz, id);
			logger.debug(String.format("deleting %s instance with id %d from database...", clazz.getTypeName(), id));
			session.delete(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.debug(String.format("problems by deleting %s with id %d from database...", clazz.getTypeName(), id));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> readAll(Class<T> clazz) {
		Transaction tx = null;
		List<T> list = new ArrayList<>();

		try (Session session = factory.openSession()) {
			tx = session.beginTransaction();
			logger.debug(String.format("getting all %s from database...", clazz.getTypeName()));
			list = session.createQuery("FROM " + clazz.getTypeName()).list();	
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.debug(String.format("problems by reading all %s from database...", clazz.getTypeName()));
		}
		return list;
	}
	
}
