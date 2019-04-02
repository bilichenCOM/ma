package hw4;

import java.util.Arrays;

public class MyArrayList<T> implements List<T> {

	private Object[] array;
	private int size = 0;

	private static final int DEFAULT_CAPACITY = 21;

	public MyArrayList(int capacity) {
		array = new Object[capacity];
	}

	public MyArrayList() {
		this(DEFAULT_CAPACITY);
	}

	public void add(T value) {
		add(value, size);
	}

	public void add(T value, int index) {
		checkIndex(index);
		if (size + 1 > array.length) {
			expandArray();
		}
		insertElementInArray(value, index);
	}

	private void insertElementInArray(T value, int validIndex) {
		if (validIndex == size) {
			array[size] = value;
		} else {
			Object[] lastElements = Arrays.copyOfRange(array, validIndex, size);
			array[validIndex++] = value;
			for (int i = 0; i < lastElements.length; i++) {
				array[i + validIndex] = lastElements[i];
			}
		}
		size++;
	}

	private void expandArray() {
		int newLength = array.length + (array.length >> 1);
		array = Arrays.copyOf(array, newLength);
	}

	public void addAll(List<T> list) {
		if (this == list) {
			throw new IllegalArgumentException("the same collection will not be duplicated");
		}

		for (int i = 0; i < list.size(); i++) {
			add(list.get(i));
		}
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {
		checkIndex(index);
		return (T) array[index];
	}

	public void set(T value, int index) {
		checkIndex(index);
		array[index] = value;
	}

	@SuppressWarnings("unchecked")
	public T remove(int index) {
		checkIndex(index);
		T element = (T) array[index];
		shiftLeft(index);
		return element;
	}

	public T remove(T t) {
		return remove(indexOf(t));
	}

	private void shiftLeft(int validIndex) {
		for (int arrayIndex = validIndex; arrayIndex < size - 1; arrayIndex++) {
			array[arrayIndex] = array[arrayIndex + 1];
		}
		array[size-- - 1] = null;

	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private void checkIndex(int index) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException("Size" + size + " but index: " + index);
		}
	}

	public int indexOf(T element) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}
}
