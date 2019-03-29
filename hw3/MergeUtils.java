package hw3;

public class MergeUtils {
	public static int[] mergeArrays(int[] firstArray, int[] secondArray) {
		int[] outputArray = new int[firstArray.length + secondArray.length];
		int firstArrayIndex = 0;
		int secondArrayIndex = 0;
		int n = 0;
		while (firstArrayIndex < firstArray.length || secondArrayIndex < secondArray.length) {
			if (firstArrayIndex < firstArray.length && secondArrayIndex < secondArray.length) {
				if (firstArray[firstArrayIndex] <= secondArray[secondArrayIndex]) {
					outputArray[n++] = firstArray[firstArrayIndex++];
				} else {
					outputArray[n++] = secondArray[secondArrayIndex++];
				}
			} else if (firstArrayIndex < firstArray.length) {
				outputArray[n++] = firstArray[firstArrayIndex++];
			} else if (secondArrayIndex < secondArray.length) {
				outputArray[n++] = secondArray[secondArrayIndex++];
			}
		}
		return outputArray;
	}
}