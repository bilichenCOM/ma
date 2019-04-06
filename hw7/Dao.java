package hw7;

import hw7.di.Injector;
import hw7.handler.ConsoleHandler;

public class Dao {
	public static void run() {
		try {
			Injector.injectDependency();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		ConsoleHandler.handle();
	}

}
