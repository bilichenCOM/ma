package hw12;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hw4.MyLinkedList;

class MyLinkedListTest extends TestUtils {
	private MyLinkedList<String> testLinkedList;

	@BeforeEach
	void setUp() throws Exception {
		testLinkedList = new MyLinkedList<>();
	}

	@Test
	void testAddE() {
		String testValue = "test_string";
		int testSize = 100;
		for (int index = 0; index < testSize; index++) {
			testLinkedList.add(testValue);
			assertEquals(testValue, testLinkedList.get(index));
		}
	}

	@Test
	void testAddEInt() {
		String testValue = "more tests";
		fillTestListWithStrings(testLinkedList, "some_test_value", 100);
		int randomIndex = randomIndex(99);
		testLinkedList.add(testValue, randomIndex);
		assertEquals(testValue, testLinkedList.get(randomIndex));
	}

	@Test
	void testAddAll() {
		MyLinkedList<String> otherList = new MyLinkedList<>();
		fillTestListWithStrings(otherList, "alphabet", 100);
		testLinkedList.addAll(otherList);
		for (int index = 0; index < otherList.size(); index++) {
			assertEquals(testLinkedList.get(index), otherList.get(index));
		}
	}

	@Test
	void testGet() {
		String testValue = "check";
		fillTestListWithStrings(testLinkedList, testValue, 100);
		int randomIndex = randomIndex(99);
		assertEquals(testValue, testLinkedList.get(randomIndex));
	}

	@Test
	void testSet() {
		String testValue = "replace value";
		fillTestListWithStrings(testLinkedList, "other value", 100);
		int randomIndex = randomIndex(99);
		testLinkedList.set(testValue, randomIndex);
		assertEquals(testValue, testLinkedList.get(randomIndex));
	}

	@Test
	void testRemoveInt() {
		String testValue = "must be removed";
		fillTestListWithStrings(testLinkedList, "common values", 100);
		int randomIndex = randomIndex(99);
		testLinkedList.add(testValue, randomIndex);
		testLinkedList.remove(randomIndex);
		assertNotEquals(testValue, testLinkedList.get(randomIndex));
	}

	@Test
	void testRemoveE() {
		String testValue = "doing tests";
		fillTestListWithStrings(testLinkedList, "string", 100);
		int randomIndex = randomIndex(100);
		testLinkedList.add(testValue, randomIndex);
		testLinkedList.remove(testValue);
		assertNotEquals(testValue, testLinkedList.get(randomIndex));
	}

	@Test
	void testSize() {
		int randomSize = randomIndex(100000);
		fillTestListWithStrings(testLinkedList, "counting", randomSize);
		assertEquals(randomSize, testLinkedList.size());
	}

	@Test
	void testIsEmpty() {
		assertTrue(testLinkedList.isEmpty());
	}
}