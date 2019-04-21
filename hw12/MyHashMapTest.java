package hw12;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hw5.MyHashMap;

class MyHashMapTest {
	private MyHashMap<String, String> testHashMap;
	private String testKey;
	private String testValue;

	@BeforeEach
	void setUp() throws Exception {
		testHashMap = new MyHashMap<>();
	}

	@Test
	void testPut() {
		testKey = "0000";
		testValue = "Codeword";
		testHashMap.put(testKey, testValue);
		Assert.assertEquals(testValue, testHashMap.get(testKey));
	}

	@Test
	void testRemove() {
		testKey = "1111";
		testValue = "to be removed";
		testHashMap.put(testKey, testValue);
		testHashMap.remove(testKey);
		Assertions.assertThrows(NoSuchElementException.class, () -> testHashMap.get(testKey), "failed");
	}

	@Test
	void testClear() {
		testHashMap.put("0999", "first test value");
		testHashMap.put("003", "second value");
		testHashMap.put("1234", "keeping tests");
		testHashMap.clear();
		Assert.assertTrue(testHashMap.size() == 0);
	}

	@Test
	void testSize() {
		int randomSize = TestUtils.randomIndex(10000);
		for (int index = 0; index < randomSize; index++) {
			testHashMap.put(Integer.toString(index), "value " + index);
		}
		Assert.assertEquals(randomSize, testHashMap.size());
	}

	@Test
	void testGet() {
		testKey = Integer.toString(TestUtils.randomIndex(9999));
		testValue = "secret information";
		testHashMap.put(testKey, testValue);
		Assert.assertEquals(testValue, testHashMap.get(testKey));
	}

	@Test
	void testGetNotExistingKey() {
		Assertions.assertThrows(NoSuchElementException.class,
				() -> testHashMap.get(Integer.toString(TestUtils.randomIndex(1000))));
	}
}