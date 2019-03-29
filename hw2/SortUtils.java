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
		double[] leftArray = Arrays.copyOfRange(array, 0, array.length / 2);
		double[] rightArray = Arrays.copyOfRange(array, array.length / 2, array.length);
		
		return mergeArrays(sortMerge(leftArray), sortMerge(rightArray));
	}

// +++++++++++++++++private methods

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
}