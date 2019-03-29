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

<<<<<<< HEAD
	public static double[] sortMerge(double[] arr) {
		if (arr.length == 1) {
			return arr;
		}
		double[] buf1 = Arrays.copyOfRange(arr, 0, arr.length / 2);
		double[] buf2 = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
		
		return mergeArrays(sortMerge(buf1), sortMerge(buf2));
=======
	public static double[] sortMerge(double[] array) {
		if (array.length == 1) {
			return array;
		}
		double[] leftArray = Arrays.copyOfRange(array, 0, array.length / 2);
		double[] rightArray = Arrays.copyOfRange(array, array.length / 2, array.length);
		
		return mergeArrays(sortMerge(leftArray), sortMerge(rightArray));
>>>>>>> hw2
	}

// +++++++++++++++++private methods

<<<<<<< HEAD
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
=======
	private static double[] mergeArrays(double[] leftArray, double[] rightArray) {
		double[] outputArray = new double[leftArray.length + rightArray.length];
		int leftArrayIndex = 0;
		int rightArrayIndex = 0;
		int outputArrayIndex = 0;
		while (leftArrayIndex < leftArray.length || rightArrayIndex < rightArray.length) {
			if (leftArrayIndex < leftArray.length && rightArrayIndex < rightArray.length) {
				outputArray[outputArrayIndex++] = mergeWithElements(leftArray, rightArray, leftArrayIndex, rightArrayIndex);
				if(leftArray[leftArrayIndex]<=rightArray[rightArrayIndex]) {
					leftArrayIndex++;
				} else {
					rightArrayIndex++;
				}
			} else if (leftArrayIndex < leftArray.length) {
				outputArray[outputArrayIndex++] = leftArray[leftArrayIndex++];
			} else if (rightArrayIndex < rightArray.length) {
				outputArray[outputArrayIndex++] = rightArray[rightArrayIndex++];
>>>>>>> hw2
			}
		}
		return outputArray;
	}
	
	private static double[] swap(double[] array, int index1, int index2) {
		double temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
		return array;
	}
	
	private static double mergeWithElements(double[] leftArray, double[] rightArray, int firstArrayIndex, int secondArrayIndex) {
		if (leftArray[firstArrayIndex] <= rightArray[secondArrayIndex]) {
			return leftArray[firstArrayIndex];
		} else {
			return  rightArray[secondArrayIndex];
		}
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