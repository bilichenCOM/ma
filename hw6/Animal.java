package hw6;

import java.io.Serializable;
import java.util.Objects;

public class Animal implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String name;

	public Animal(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Animal) {
			return Objects.equals(name, ((Animal) obj).name);
		}
		return false;
	}

	public String toString() {
		return this.name;
	}
}