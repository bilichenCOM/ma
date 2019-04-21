package hw12;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hw4.MyLinkedList;

class MyLinkedListTest {
	private MyLinkedList<String> testLinkedList;

	@BeforeEach
	void setUp() {
		testLinkedList = new MyLinkedList<>();
	}

	@Test
	void testAddE() {
		String testValue = "test_string";
		int testSize = 100;
		for (int index = 0; index < testSize; index++) {
			testLinkedList.add(testValue);
			Assert.assertEquals(testValue, testLinkedList.get(index));
		}
	}

	@Test
	void testAddEInt() {
		String testValue = "more tests";
		TestUtils.fillTestListWithStrings(testLinkedList, "some_test_value", 100);
		int randomIndex = TestUtils.randomIndex(99);
		testLinkedList.add(testValue, randomIndex);
		Assert.assertEquals(testValue, testLinkedList.get(randomIndex));
	}

	@Test
	void testAddAll() {
		MyLinkedList<String> otherList = new MyLinkedList<>();
		TestUtils.fillTestListWithStrings(otherList, "alphabet", 100);
		testLinkedList.addAll(otherList);
		for (int index = 0; index < otherList.size(); index++) {
			Assert.assertEquals(testLinkedList.get(index), otherList.get(index));
		}
	}

	@Test
	void testGet() {
		String testValue = "check";
		TestUtils.fillTestListWithStrings(testLinkedList, testValue, 100);
		int randomIndex = TestUtils.randomIndex(99);
		Assert.assertEquals(testValue, testLinkedList.get(randomIndex));
	}

	@Test
	void testGetOutOfBounds() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> testLinkedList.get(1 + TestUtils.randomIndex(100)));
	}

	@Test
	void testSet() {
		String testValue = "replace value";
		TestUtils.fillTestListWithStrings(testLinkedList, "other value", 100);
		int randomIndex = TestUtils.randomIndex(99);
		testLinkedList.set(testValue, randomIndex);
		Assert.assertEquals(testValue, testLinkedList.get(randomIndex));
	}

	@Test
	void testRemoveInt() {
		String testValue = "must be removed";
		TestUtils.fillTestListWithStrings(testLinkedList, "common values", 100);
		int randomIndex = TestUtils.randomIndex(99);
		testLinkedList.add(testValue, randomIndex);
		testLinkedList.remove(randomIndex);
		Assert.assertNotEquals(testValue, testLinkedList.get(randomIndex));
	}

	@Test
	void testRemoveE() {
		String testValue = "doing tests";
		TestUtils.fillTestListWithStrings(testLinkedList, "string", 100);
		int randomIndex = TestUtils.randomIndex(100);
		testLinkedList.add(testValue, randomIndex);
		testLinkedList.remove(testValue);
		Assert.assertNotEquals(testValue, testLinkedList.get(randomIndex));
	}

	@Test
	void testSize() {
		int randomSize = TestUtils.randomIndex(100000);
		TestUtils.fillTestListWithStrings(testLinkedList, "counting", randomSize);
		Assert.assertEquals(randomSize, testLinkedList.size());
	}

	@Test
	void testIsEmpty() {
		Assert.assertTrue(testLinkedList.isEmpty());
	}
}