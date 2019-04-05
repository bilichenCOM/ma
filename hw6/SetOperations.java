package hw6;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetOperations {
	public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
		Set<T> biggerSet = new HashSet<>();
		Set<T> smallerSet = new HashSet<>();
		if (set1.size() < set2.size()) {
			biggerSet.addAll(set2);
			smallerSet.addAll(set1);
		} else {
			biggerSet.addAll(set1);
			smallerSet.addAll(set2);
		}
		Iterator<T> it = smallerSet.iterator();
		while (it.hasNext()) {
			T value = it.next();
			if (!biggerSet.remove(value)) {
				biggerSet.add(value);
			}
		}
		return biggerSet;
	}
}
