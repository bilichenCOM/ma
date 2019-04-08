package hw7.model;

public class Client extends Creature {
	private static final long serialVersionUID = 1L;

	private String name;
	private String phoneNumber;

	public Client(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Client{" + "name='" + name + '\'' + ", phoneNumber='" + phoneNumber + '\'' + '}';
	}

}
