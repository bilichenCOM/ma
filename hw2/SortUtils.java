package hw2;

import java.util.Arrays;

public class SortUtils {

	public static final double COMB_IND = 1.2473309;

	public static double[] sortBuble(double[] arr) {
		int len = arr.length;
		boolean sorted = false;

		while (!sorted) {
			sorted = true;
			for (int i = 0; i < len - 1; i++) {
				if (arr[i] > arr[i + 1]) {
					double next = arr[i + 1];
					arr[i + 1] = arr[i];
					arr[i] = next;
					sorted = false;
				}
			}
		}
		return arr;
	}

	public static double[] sortMerge(double[] arr) {
		if (arr.length == 1)
			return arr;
		double[] buf1 = Arrays.copyOfRange(arr, 0, arr.length / 2);
		double[] buf2 = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
		return mergeArrays(sortMerge(buf1), sortMerge(buf2));
	}

// +++++++++++++++++private methods

	private static double[] mergeArrays(double[] arr1, double[] arr2) {
		final int k = arr1.length;
		final int l = arr2.length;
		double[] merged = new double[k + l];
		int i = 0;
		int j = 0;
		int n = 0;
		while (i < k || j < l) {
			if (i < k && j < l) {
				if (arr1[i] <= arr2[j]) {
					merged[n] = arr1[i];
					i++;
				} else {
					merged[n] = arr2[j];
					j++;
				}
			} else if (i < k) {
				merged[n] = arr1[i];
				i++;
			} else if (j < l) {
				merged[n] = arr2[j];
				j++;
			}
			n++;
		}
		return merged;
	}
}