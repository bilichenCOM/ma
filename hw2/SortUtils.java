package hw2;

import java.util.Arrays;

public class SortUtils {

	public static double[] sortBuble(double[] array) {
		int len = array.length;
		boolean sorted = false;

		while (!sorted) {
			sorted = true;
			for (int i = 0; i < len - 1; i++) {
				if (array[i] > array[i + 1]) {
					array = swap(array, i, i+1);
					sorted = false;
				}
			}
		}
		return array;
	}

	public static double[] sortMerge(double[] arr) {
		if (arr.length == 1)
			return arr;
		double[] buf1 = Arrays.copyOfRange(arr, 0, arr.length / 2);
		double[] buf2 = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
		return mergeArrays(sortMerge(buf1), sortMerge(buf2));
	}

// +++++++++++++++++private methods

	private static double[] mergeArrays(double[] array1, double[] array2) {
		double[] merged = new double[array1.length + array2.length];
		int i = 0;
		int j = 0;
		int n = 0;
		while (i < array1.length || j < array2.length) {
			if (i < array1.length && j < array2.length) {
				merged = mergeWithElements(array1, array2, i, j, merged, n);
				i++;
				j++;
			} else if (i < array1.length) {
				merged[n] = array1[i];
				i++;
			} else if (j < array2.length) {
				merged[n] = array2[j];
				j++;
			}
			n++;
		}
		return merged;
	}
	
	private static double[] swap(double[] array, int index1, int index2) {
		double temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
		return array;
	}
	
	private static double[] mergeWithElements(double[] array1,double[] array2, int index1, int index2, double[] output, int index) {
		if (array1[index1] <= array2[index2]) {
			output[index] = array1[index1];
		} else {
			output[index] = array2[index2];
		}
		return output;
	}
}