package hw3;

public class MergeUtils {
	public static int[] mergeArrays(int[] a1, int[] a2) {
		int[] merged = new int[a1.length + a2.length];
		int i = 0;
		int j = 0;
		int n = 0;
		while (i < a1.length || j < a2.length) {
			if (i < a1.length && j < a2.length) {
				if (a1[i] <= a2[j]) {
					merged[n] = a1[i];
					i++;
				} else {
					merged[n] = a2[j];
					j++;
				}
			} else if (i < a1.length) {
				merged[n] = a1[i];
				i++;
			} else if (j < a2.length) {
				merged[n] = a2[j];
				j++;
			}
			n++;
		}
		return merged;
	}
}