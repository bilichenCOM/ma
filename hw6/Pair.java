
package hw6;

import java.util.Objects;

public class Pair<A, B> {
	private A firstElement;
	private B secondElement;

	private Pair(A firstElement, B secondElement) {
		this.firstElement = firstElement;
		this.secondElement = secondElement;
	}

	public static <A, B> Pair<A, B> of(A firstElement, B secondElement) {
		return new Pair<>(firstElement, secondElement);
	}

	public A getFirst() {
		return firstElement;
	}

	public B getSecond() {
		return secondElement;
	}

	@SuppressWarnings("unchecked")
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (this == o) {
			return true;
		}

		if (o.getClass() == getClass()) {
			Pair<A, B> otherPair = (Pair<A, B>) o;
			return Objects.equals(firstElement, otherPair.getFirst())
					&& Objects.equals(secondElement, otherPair.getSecond());
		}
		return false;
	}

	public int hashCode() {
		return (firstElement != null && secondElement != null) ? firstElement.hashCode() + secondElement.hashCode()
				: (firstElement == null) ? secondElement.hashCode()
						: (secondElement == null) ? firstElement.hashCode() : 0;
	}
}