package hw7.handler;

import hw7.dao.CreatureDao;
import hw7.di.Inject;
import hw7.model.Client;
import hw7.model.Creature;
import hw7.model.Human;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleHandler {

	private static Scanner scanner;

	@Inject
	private static CreatureDao creatureDao;

	public static void handle() {
		runCLI();
	}

	public static void runCLI() {
		scanner = new Scanner(System.in);
		System.out.println(ConsoleExpressions.START);
		readFromConsole();
	}

	private static void readFromConsole() {
		System.out.println(ConsoleExpressions.MENU);
		String answer = scanner.nextLine();
		switch (answer) {
		case "1":
			addClientInfo();
			break;
		case "2":
			getCreatureInfo();
			break;
		case "3":
			exit();
			return;
		case "4":
			addHumanInfo();
			break;
		default:
			System.out.println(ConsoleExpressions.MISMATCH);
			break;
		}
		readFromConsole();
	}

	private static void addClientInfo() {
		System.out.println(ConsoleExpressions.CLIENT_START);
		System.out.println(ConsoleExpressions.CLIENT_NAME);
		String name = scanner.nextLine();
		System.out.println(ConsoleExpressions.CLIENT_NUMBER);
		String phone = scanner.nextLine();
		Creature client = new Client(name, phone);
		creatureDao.save(client);
	}

	private static void addHumanInfo() {
		try {
			System.out.println(ConsoleExpressions.HUMAN_START);
			System.out.println(ConsoleExpressions.HUMAN_NAME);
			String name = scanner.nextLine();
			System.out.println(ConsoleExpressions.HUMAN_AGE);
			int age = Integer.parseInt(scanner.nextLine());
			System.out.println(ConsoleExpressions.HUMAN_COUNTRY);
			String country = scanner.nextLine();
			Creature human = new Human(name, age, country);
			creatureDao.save(human);
		} catch (InputMismatchException | NumberFormatException ex) {
			System.out.println(ConsoleExpressions.MISMATCH);
		}

	}

	private static void getCreatureInfo() {
		try {
			System.out.println(ConsoleExpressions.INFO);
			creatureDao.get().forEach(System.out::println);
		} catch (Exception e) {
			System.out.println(ConsoleExpressions.ERROR);
		}
	}

	private static void exit() {
		if (scanner != null) {
			System.out.println(ConsoleExpressions.CLOSE);
			scanner.close();
		}
	}
}
