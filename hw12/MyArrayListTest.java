package hw12;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hw4.MyArrayList;

class MyArrayListTest {
	private MyArrayList<String> testArrayList;

	@SuppressWarnings("rawtypes")
	private static final Class myArrayListClazz = MyArrayList.class;
	private Field arrayField;
	private Field sizeField;

	private Object[] array;

	@BeforeAll
	static void setUpBeforeClass() {
		System.out.println("Test are begined...");
	}

	@AfterAll
	static void tearDownAfterClass() {
		System.out.println("Test are ended!");
	}

	@BeforeEach
	void setUp() throws NoSuchFieldException, SecurityException {
		testArrayList = new MyArrayList<>();
		arrayField = myArrayListClazz.getDeclaredField("array");
		arrayField.setAccessible(true);
		sizeField = myArrayListClazz.getDeclaredField("size");
		sizeField.setAccessible(true);
	}

	@Test
	void testTestMyArrayList() throws IllegalArgumentException, IllegalAccessException {
		testArrayList = new MyArrayList<>(1000);
		array = (Object[]) arrayField.get(testArrayList);
		Assert.assertEquals(1000, array.length);
	}

	@Test
	void testTestAddE() throws IllegalArgumentException, IllegalAccessException {
		String testValue = "hello test value";
		testArrayList.add(testValue);
		array = (Object[]) arrayField.get(testArrayList);
		Assert.assertEquals(testValue, array[0]);
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
		Assert.assertEquals(testValue, array[randomIndex]);
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
		Assert.assertArrayEquals(otherArray, array);
	}

	@Test
	void testTestGet()
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		String testValue = "hello value";
		Object[] testArray = TestUtils.makeTestArray(testValue, 100);
		arrayField.set(testArrayList, testArray);
		sizeField.set(testArrayList, testArray.length);
		int randomIndex = (int) (Math.random() * (testArray.length - 1));
		Assert.assertEquals(testValue + randomIndex, testArrayList.get(1 + randomIndex));
	}
	
	@Test
	void testGetOutOfBound() {
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> testArrayList.get(TestUtils.randomIndex(100)));
	}

	@Test
	void testTestSet()
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		String testValue = "hello value";
		Object[] testArray = TestUtils.makeTestArray("string", 100);
		arrayField.set(testArrayList, testArray);
		sizeField.set(testArrayList, testArray.length);
		int randomIndex = TestUtils.randomIndex(99);
		testArrayList.set(testValue, randomIndex);
		array = (Object[]) arrayField.get(testArrayList);
		Assert.assertEquals(testValue, array[randomIndex]);
	}

	@Test
	void testTestRemoveInt() throws IllegalArgumentException, IllegalAccessException {
		Object[] testArray = TestUtils.makeTestArray("hello", 100);
		int randomIndex = TestUtils.randomIndex(testArray.length - 1);
		String testValue = "remove me";
		testArray[randomIndex] = testValue;
		arrayField.set(testArrayList, testArray);
		sizeField.set(testArrayList, testArray.length);
		testArrayList.remove(randomIndex);
		array = (Object[]) arrayField.get(testArrayList);
		Assert.assertNotEquals(testValue, array[randomIndex]);
	}

	@Test
	void testTestRemoveE() throws IllegalArgumentException, IllegalAccessException {
		String testValue = "remove me";
		Object[] testArray = TestUtils.makeTestArray("strings", 100);
		int index = 25;
		testArray[index] = testValue;
		arrayField.set(testArrayList, testArray);
		sizeField.set(testArrayList, testArray.length);
		testArrayList.remove(testValue);
		array = (Object[]) arrayField.get(testArrayList);
		for (Object o : array) {
			if (o != null && o.equals(testValue)) {
				Assert.fail();
			}
		}
	}

	@Test
	void testTestSize() {
		int testSize = 100;
		for (int count = 0; count < testSize; count++) {
			testArrayList.add("value");
		}
		Assert.assertEquals(testSize, testArrayList.size());
	}

	@Test
	void testTestIsEmpty() {
		Assert.assertTrue(testArrayList.isEmpty());
	}
}
