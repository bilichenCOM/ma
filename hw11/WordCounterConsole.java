package hw11;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WordCounterConsole {

	public static void run() {
		String line = readFromConsole().replaceAll("[^a-zA-Zа-яА-Я0-9]+", " ").toLowerCase();
		List<String> list = Arrays.asList(line.split(" "));
		list.stream()
			.distinct()
			.sorted()
			.sorted((w1,w2) -> -1 
								* (int) (list.stream().filter(w -> w.equals(w1)).count()
										- list.stream().filter(w -> w.equals(w2)).count()))
			.limit(10).forEach(System.out::println);
	}

	private static String readFromConsole() {
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		scanner.close();
		return line;
	}
}