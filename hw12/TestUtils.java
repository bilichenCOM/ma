package hw12;

import hw4.MyLinkedList;

public class TestUtils {

	public static int randomIndex(int maxValue) {
		return (int) (Math.random() * maxValue);
	}

	public static Object[] makeTestArray(String testValue, int size) {
		Object[] testArray = new Object[size];
		for (int index = 0; index < testArray.length; index++) {
			testArray[index] = testValue + index;
		}
		return testArray;
	}

	public static void fillTestListWithStrings(MyLinkedList<String> list, String testValue, int size) {
		for (int index = 0; index < size; index++) {
			list.add(testValue);
		}
	}
}