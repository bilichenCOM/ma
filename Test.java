import hw7.Dao;

public class Test {
<<<<<<< HEAD
	public static void main(String[] args) throws IllegalAccessException {
		Dao.run();
=======
	public static void main(String[] args) throws IOException {
		animalTest();
		pairTest();
		setOpsTest();
		accountantOfficeTest();
	}

	/*
	 * 
	 * the accountantOfficeTest() code is just copy-pasted from
	 * https://stepik.org/lesson/12781/step/14?unit=3128
	 * 
	 */
	private static void accountantOfficeTest() {
		messageTest();
		salaryTest();
	}

	private static void salaryTest() {
		// Некоторая случайная строка. Можете выбрать ее самостоятельно.
		String randomFrom = "...";
		String randomTo = "...";
		int randomSalary = 100;

		Salary salary1 = new Salary("Facebook", "Mark Zuckerberg", 1);
		Salary salary2 = new Salary("FC Barcelona", "Lionel Messi", Integer.MAX_VALUE);
		Salary salary3 = new Salary(randomFrom, randomTo, randomSalary);

		MailService<Integer> salaryService = new MailService<>();

		Arrays.asList(salary1, salary2, salary3).forEach(salaryService);

		Map<String, List<Integer>> salaries = salaryService.getMailBox();
		assert salaries.get(salary1.getTo()).equals(Arrays.asList(1)) : "wrong salaries mailbox content (1)";
		assert salaries.get(salary2.getTo())
				.equals(Arrays.asList(Integer.MAX_VALUE)) : "wrong salaries mailbox content (2)";
		assert salaries.get(randomTo).equals(Arrays.asList(randomSalary)) : "wrong salaries mailbox content (3)";

	}

	private static void messageTest() {
		// Random variables
		String randomTo = "...";

		MailMessage firstMessage = new MailMessage("Robert Howard", "H.P. Lovecraft",
				"This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!");

		assert firstMessage.getFrom().equals("Robert Howard") : "Wrong firstMessage from address";
		assert firstMessage.getTo().equals("H.P. Lovecraft") : "Wrong firstMessage to address";
		assert firstMessage.getContent().endsWith("Howard!") : "Wrong firstMessage content ending";

		MailMessage secondMessage = new MailMessage("Jonathan Nolan", "Christopher Nolan",
				"Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!");

		MailMessage thirdMessage = new MailMessage("Stephen Hawking", "Christopher Nolan",
				"Я так и не понял Интерстеллар.");

		List<MailMessage> messages = Arrays.asList(firstMessage, secondMessage, thirdMessage);

		MailService<String> mailService = new MailService<>();

		messages.stream().forEachOrdered(mailService);

		Map<String, List<String>> mailBox = mailService.getMailBox();

		assert mailBox.get("H.P. Lovecraft").equals(Arrays.asList(
				"This \"The Shadow over Innsmouth\" story is real masterpiece, Howard!")) : "wrong mailService mailbox content (1)";

		assert mailBox.get("Christopher Nolan").equals(Arrays.asList(
				"Брат, почему все так хвалят только тебя, когда практически все сценарии написал я. Так не честно!",
				"Я так и не понял Интерстеллар.")) : "wrong mailService mailbox content (2)";

		assert mailBox.get(randomTo).equals(Collections.<String>emptyList()) : "wrong mailService mailbox content (3)";

	}// end of AccountantOffice test series

	/*
	 *
	 * 
	 */
	private static void setOpsTest() {
		System.out.println("This is SetOperations test:");
		Set<Integer> num1 = new HashSet<>();
		num1.add(3);
		num1.add(7);
		num1.add(9);
		num1.add(23);
		System.out.println(num1);

		HashSet<Integer> num2 = new HashSet<>();
		num2.add(5);
		num2.add(7);
		num2.add(12);
		num2.add(9);
		num2.add(23);
		System.out.println(num2);

		HashSet<String> str = new HashSet<>();
		str.add("jsklf");
		str.add("hello@)");
		System.out.println(SetOperations.symmetricDifference(str, num1));

		Set<Integer> result = SetOperations.symmetricDifference(num1, num2);
		System.out.println(result);
		System.out.println("////////////////\r\n");
	}

	private static void pairTest() {
		System.out.println("This is pairTest");
		Pair<String, Integer> pair = Pair.of("this is a text", 841947);
		Pair<String, Integer> pair2 = Pair.of("this is a text", 841947);
		System.out.println(pair.equals(pair2));
		Pair<String, Integer> pair3 = Pair.of(null, 841947);
		System.out.println(pair2.equals(pair3));
		System.out.println("/////////////////////\r\n");
	}

	private static void animalTest() throws IOException {
		System.out.println("This is animalTest");
		Animal[] animalM1 = getTestAnimalArray();
		System.out.println(Arrays.deepToString(animalM1));

		byte[] serialAnimal = writeToByteArray(animalM1);
		Animal[] animalM2 = AnimalDecoder.deserializeAnimalArray(serialAnimal);
		System.out.println(Arrays.deepToString(animalM2));
		System.out.println("/////////////\r\n");
	}

	private static byte[] writeToByteArray(Animal[] animals) throws IOException {
		ByteArrayOutputStream bai = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bai);
		oos.writeInt(animals.length);
		for (int i = 0; i < animals.length; i++) {
			oos.writeObject(animals[i]);
		}
		oos.flush();
		oos.close();
		return bai.toByteArray();
	}

	private static Animal[] getTestAnimalArray() {
		return new Animal[] { new Animal("Cat"), new Animal("Dog"), new Animal("Elephant"), new Animal("Cock"),
				new Animal("Bull"), new Animal("Ant"), new Animal("Tentecles"), new Animal("Worm") };
>>>>>>> hw6
	}
}