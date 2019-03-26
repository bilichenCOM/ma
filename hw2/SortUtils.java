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

	public static double[] sortShaker(double[] arr) {
		int begin = 0;
		int end = arr.length - 1;
		boolean sorted = false;

		while (!sorted) {
			sorted = true;
			for (int i = begin; i < end; i++) {
				if (arr[i] > arr[i + 1]) {
					double next = arr[i + 1];
					arr[i + 1] = arr[i];
					arr[i] = next;
					sorted = false;
				}
			}
			if (sorted)
				break;
			end--;
			for (int i = end; i > begin; i--) {
				if (arr[i] < arr[i - 1]) {
					double next = arr[i - 1];
					arr[i - 1] = arr[i];
					arr[i] = next;
				}
			}
			begin++;
		}
		return arr;
	}

	public static double[] sortComb(double[] arr) {
		int step = arr.length - 1;
		while (step > 1) {
			for (int i = 0; i + step < arr.length; i++) {
				if (arr[i] > arr[i + 1]) {
					double next = arr[i + step];
					arr[i + step] = arr[i];
					arr[i] = next;
				}
			}
			step /= COMB_IND;
		}
		return sortBuble(arr);
	}

	public static double[] sortInsert(double[] arr) {
		double[] newArr = new double[arr.length];

		for (int i = 0; i < newArr.length; i++) {
			boolean isMin = false;
			while (!isMin) {
				isMin = true;
				for (int j = i; j < arr.length; j++) {
					if (arr[i] > arr[j]) {
						double next = arr[j];
						arr[j] = arr[i];
						arr[i] = next;
						isMin = false;
					}
				}
			}
			newArr[i] = arr[i];
		}
		return newArr;
	}

	public static double[] sortSelection(double[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int minIndex = getMinIndex(arr, i);
			double min = arr[minIndex];
			arr[minIndex] = arr[i];
			arr[i] = min;
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

//	+++++++++++++++++private methods

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

	private static int getMinIndex(double[] arr, int start) {
		int minIndex = start;
		double min = arr[minIndex];
		for (int i = start; i < arr.length; i++) {
			if (arr[i] < min) {
				min = arr[i];
				minIndex = i;
			}
		}
		return minIndex;
	}

}