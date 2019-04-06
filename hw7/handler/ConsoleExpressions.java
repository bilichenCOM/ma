package hw7.handler;

public enum ConsoleExpressions {
	START("+++++++DaoConsole++++++++"),
	MENU("1 - если вносите клиента,\r\n"
			+ "2 - если получаете клиента,\r\n"
			+ "3 - для выхода,\r\n"
			+ "4 - внести человека\r\n"
			+ "5 - получить человека"),
	CLIENT_START("Введите информацию о клиенте"), CLIENT_NAME("Введите имя клиента"),
	CLIENT_NUMBER("Введите телефон"),CLIENT_NO("No clients"), CLIENT_INFO("Here is Client info:"),
	HUMAN_START("You can add Human right now"), HUMAN_NAME("Please enter the name:"),
	HUMAN_AGE("Enter the age:"), HUMAN_COUNTRY("Enter the country:"), 
	HUMAN_NO("Here is not any human"), HUMAN_INFO("This is a Human"),
	MISMATCH("Неправильный ввод!"),	ERROR("Случилась ошибка!"),	
	DONE("Done!"), CLOSE("------closed-------");

	private String text;

	private ConsoleExpressions(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public String toString() {
		return text;
	}
}
