package db.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import db.GenericDao;
import utils.HibernateUtil;

public abstract class GenericDaoImpl<T> implements GenericDao<T>{

	private static final SessionFactory factory = HibernateUtil.getSessionFactory();

	@Override
	public void add(T t) {
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Optional<T> read(Class<T> clazz, Long id) {
		Session session = factory.openSession();
		T t = session.get(clazz, id);
		session.close();
		return Optional.of(t);
	}

	@Override
	public void update(T t) {
		Session session = factory.openSession();
		session.beginTransaction();
		session.update(t);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(Class<T> clazz, Long id) {
		Session session = factory.openSession();
		session.beginTransaction();
		T t = session.get(clazz, id);
		session.delete(t);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<T> readAll(Class<T> clazz) {
		Session session = factory.openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<T> list = session.createQuery("FROM " + clazz.getTypeName()).list();
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
}
