package hw7.di;

import java.lang.reflect.Field;

import hw7.dao.CreatureDao;
import hw7.dao.FileCreatureDao;
import hw7.dao.InMemoryCreatureDao;
import hw7.factory.ClientDaoFactory;
import hw7.handler.ConsoleHandler;

public class Injector {

	public static void injectDependency() throws IllegalAccessException {
		Class<InMemoryCreatureDao> inMemoryClazz = InMemoryCreatureDao.class;
		Class<FileCreatureDao> fileClazz = FileCreatureDao.class;
		Class<ConsoleHandler> handlerClazz = ConsoleHandler.class;

		boolean inMemoryAvailable = inMemoryClazz.isAnnotationPresent(Component.class);
		boolean fileAvailable = fileClazz.isAnnotationPresent(Component.class);

		Field[] fields = handlerClazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Inject.class)) {
				field.setAccessible(true);
				CreatureDao creatureDao = ClientDaoFactory.getCreatureDao(fileAvailable, inMemoryAvailable);
				field.set(null, creatureDao);
			}
		}
	}
}
