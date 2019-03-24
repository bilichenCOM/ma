package hw2;

public interface Predator {

	public static void eat() {
		System.out.println("I like this piece of meat");
	}
}

interface Cat extends Predator{

	void eat();
	
	
}
