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

	public static double[] sortMerge(double[] array) {
		if (array.length == 1) {
			return array;
		}
		double[] buf1 = Arrays.copyOfRange(array, 0, array.length / 2);
		double[] buf2 = Arrays.copyOfRange(array, array.length / 2, array.length);
		
		return mergeArrays(sortMerge(buf1), sortMerge(buf2));
	}

// +++++++++++++++++private methods

	private static double[] mergeArrays(double[] array1, double[] array2) {
		double[] merged = new double[array1.length + array2.length];
		int firstArrayIndex = 0;
		int secondArrayIndex = 0;
		int n = 0;
		while (firstArrayIndex < array1.length || secondArrayIndex < array2.length) {
			if (firstArrayIndex < array1.length && secondArrayIndex < array2.length) {
				merged[n++] = mergeWithElements(array1, array2, firstArrayIndex, secondArrayIndex);
				if(array1[firstArrayIndex]<=array2[secondArrayIndex]) {
					firstArrayIndex++;
				} else {
					secondArrayIndex++;
				}
			} else if (firstArrayIndex < array1.length) {
				merged[n++] = array1[firstArrayIndex++];
			} else if (secondArrayIndex < array2.length) {
				merged[n++] = array2[secondArrayIndex++];
			}
		}
		return merged;
	}
	
	private static double[] swap(double[] array, int index1, int index2) {
		double temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
		return array;
	}
	
	private static double mergeWithElements(double[] array1,double[] array2, int firstArrayIndex, int secondArrayIndex) {
		if (array1[firstArrayIndex] <= array2[secondArrayIndex]) {
			return array1[firstArrayIndex];
		} else {
			return  array2[secondArrayIndex];
		}
	}
}