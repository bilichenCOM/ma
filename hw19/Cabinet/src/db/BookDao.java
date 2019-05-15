package db;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Book;

public class BookDao implements CabinetCrud<Book>{
	private SessionFactory factory = HibernateUtil.getSessionFactory();

	public BookDao() {
	}

	@Override
	public void add(Book book) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(book);
		tx.commit();
		session.close();
	}

	@Override
	public Optional<Book> read(Long id) {
		Session session = factory.openSession();
		Book book = session.get(Book.class, id);
		session.close();
		return Optional.of(book);
	}

	@Override
	public void update(Book book) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(book);
		tx.commit();
		session.close();
	}

	@Override
	public void delete(Long id) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Book book = session.get(Book.class, id);
		session.delete(book);
		tx.commit();
		session.close();
	}

	@Override
	public List<Book> readAll() {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String queryString = "SELECT b FROM Book b";
		@SuppressWarnings("unchecked")
		List<Book> bookList = session.createQuery(queryString).list();
		tx.commit();
		session.close();
		return bookList;
	}
	
}
