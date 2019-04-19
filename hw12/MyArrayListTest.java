package hw12;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hw4.MyArrayList;

class MyArrayListTest extends TestUtils {
	private MyArrayList<String> testArrayList;

	@SuppressWarnings("rawtypes")
	private static final Class myArrayListClazz = MyArrayList.class;
	private Field arrayField;
	private Field sizeField;

	private Object[] array;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Test are begined...");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("Test are ended!");
	}

	@BeforeEach
	void setUp() throws Exception {
		testArrayList = new MyArrayList<>();
		arrayField = myArrayListClazz.getDeclaredField("array");
		arrayField.setAccessible(true);
		sizeField = myArrayListClazz.getDeclaredField("size");
		sizeField.setAccessible(true);
	}

	@Test
	void testTestMyLinkedList() throws Exception {
		testArrayList = new MyArrayList<>(1000);
		array = (Object[]) arrayField.get(testArrayList);
		assertEquals(1000, array.length);
	}

	@Test
	void testTestAddE() throws IllegalArgumentException, IllegalAccessException {
		String testValue = "hello test value";
		testArrayList.add(testValue);
		array = (Object[]) arrayField.get(testArrayList);
		assertEquals(testValue, array[0]);
	}

	@Test
	void testTestAddEInt() throws IllegalArgumentException, IllegalAccessException {
		String testValue = "add with index test";
		int testSize = 10;
		for (int count = 0; count < testSize; count++) {
			testArrayList.add("string");
		}
		int randomIndex = (int) (Math.random() * testSize);
		testArrayList.add(testValue, randomIndex);
		array = (Object[]) arrayField.get(testArrayList);
		assertEquals(testValue, array[randomIndex]);
	}

	@Test
	void testTestAddAll() throws IllegalArgumentException, IllegalAccessException {
		MyArrayList<String> otherList = new MyArrayList<>();
		for (int count = 0; count < 100; count++) {
			otherList.add("value #" + count);
		}
		testArrayList.addAll(otherList);
		Object[] otherArray = (Object[]) arrayField.get(otherList);
		array = (Object[]) arrayField.get(testArrayList);
		assertArrayEquals(otherArray, array);
	}

	@Test
	void testTestGet()
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		String testValue = "hello value";
		Object[] testArray = makeTestArray(testValue, 100);
		arrayField.set(testArrayList, testArray);
		sizeField.set(testArrayList, testArray.length);
		int randomIndex = (int) (Math.random() * (testArray.length - 1));
		assertEquals(testValue + randomIndex, testArrayList.get(randomIndex));
	}

	@Test
	void testTestSet()
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		String testValue = "hello value";
		Object[] testArray = makeTestArray("string", 100);
		arrayField.set(testArrayList, testArray);
		sizeField.set(testArrayList, testArray.length);
		int randomIndex = randomIndex(99);
		testArrayList.set(testValue, randomIndex);
		array = (Object[]) arrayField.get(testArrayList);
		assertEquals(testValue, array[randomIndex]);
	}

	@Test
	void testTestRemoveInt() throws IllegalArgumentException, IllegalAccessException {
		Object[] testArray = makeTestArray("hello", 100);
		int randomIndex = randomIndex(testArray.length - 1);
		String testValue = "remove me";
		testArray[randomIndex] = testValue;
		arrayField.set(testArrayList, testArray);
		sizeField.set(testArrayList, testArray.length);
		testArrayList.remove(randomIndex);
		array = (Object[]) arrayField.get(testArrayList);
		assertNotEquals(testValue, array[randomIndex]);
	}

	@Test
	void testTestRemoveE() throws IllegalArgumentException, IllegalAccessException {
		String testValue = "remove me";
		Object[] testArray = makeTestArray("strings", 100);
		int index = 25;
		testArray[index] = testValue;
		arrayField.set(testArrayList, testArray);
		sizeField.set(testArrayList, testArray.length);
		testArrayList.remove(testValue);
		array = (Object[]) arrayField.get(testArrayList);
		for (Object o : array) {
			if (o != null && o.equals(testValue)) {
				fail();
			}
		}
	}

	@Test
	void testTestSize() {
		int testSize = 100;
		for (int count = 0; count < testSize; count++) {
			testArrayList.add("value");
		}
		assertEquals(testSize, testArrayList.size());
	}

	@Test
	void testTestIsEmpty() {
		assertTrue(testArrayList.isEmpty());
	}
}
