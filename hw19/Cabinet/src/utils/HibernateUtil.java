package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import model.Book;
import model.Card;
import model.Good;
import model.Protein;
import model.Purchase;
import model.User;

public class HibernateUtil {

	private static SessionFactory factory;

	public static SessionFactory getSessionFactory() {
		if (factory == null) {
			Configuration configuration = new Configuration().configure();
			configuration.addAnnotatedClass(User.class);
			configuration.addAnnotatedClass(Book.class);
			configuration.addAnnotatedClass(Purchase.class);
			configuration.addAnnotatedClass(Good.class);
			configuration.addAnnotatedClass(Card.class);
			configuration.addAnnotatedClass(Protein.class);

			ServiceRegistry registry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties())
					.build();

			factory = configuration.buildSessionFactory(registry);
		}
		return factory;
	}
}