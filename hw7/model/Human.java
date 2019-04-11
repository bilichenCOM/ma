package hw7.model;

public class Human extends Creature {
	private static final long serialVersionUID = 1L;

	private String name;
	private int age;
	private String country;

	public Human(String name, int age, String country) {
		this.name = name;
		this.age = age;
		this.country = country;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Human [name=" + name + ", age=" + age + ", country=" + country + "]";
	}
}