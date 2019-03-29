package hw3;

public class MergeUtils {
	public static int[] mergeArrays(int[] a1, int[] a2) {
		final int k = a1.length;
		final int l = a2.length;
		int[] merged = new int[k + l];
		int i = 0;
		int j = 0;
		int n = 0;
		while (i < k || j < l) {
			if (i < k && j < l) {
				if (a1[i] <= a2[j]) {
					merged[n] = a1[i];
					i++;
				} else {
					merged[n] = a2[j];
					j++;
				}
			} else if (i < k) {
				merged[n] = a1[i];
				i++;
			} else if (j < l) {
				merged[n] = a2[j];
				j++;
			}
			n++;
		}
		return merged;
	}
}