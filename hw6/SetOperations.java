package hw6;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {
	public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
		Set<T> result = new HashSet<>();
		Set<T> temp = new HashSet<>();
		Set<T> mutableSet1 = new HashSet<>(set1);
		Set<T> mutableSet2 = new HashSet<>(set2);
		temp.addAll(set1);
		mutableSet1.removeAll(set2);
		temp.remove(set1);
		mutableSet2.removeAll(temp);
		result.addAll(set1);
		result.addAll(set2);
		return result;
	}
}