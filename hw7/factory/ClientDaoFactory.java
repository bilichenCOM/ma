package hw7.factory;

import hw7.dao.CreatureDao;
import hw7.dao.FileCreatureDao;
import hw7.dao.InMemoryCreatureDao;
import hw7.service.PropertyLoader;

import java.io.IOException;

public class ClientDaoFactory {

	private static final CreatureDao inMemoryDao = new InMemoryCreatureDao();
	private static final CreatureDao fileDao = new FileCreatureDao();

	public static CreatureDao getCreatureDao(boolean isFileDao, boolean isInMemoryDao) {
		try {
			String dbName = PropertyLoader.getProperty();
			if (dbName.equals("memory") && isInMemoryDao) {
				return inMemoryDao;
			}
		} catch (IOException e) {
			System.out.println("Нет доступа к файлу. Please provaide properties file");
		}
		if (isFileDao) {
			return fileDao;
		} else {
			throw new ComponentNotFoundException();
		}
	}
}
