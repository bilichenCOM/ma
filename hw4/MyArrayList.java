package hw4;

import java.util.NoSuchElementException;

public class MyArrayList<T> implements List<T> {

	private Object[] array;
	private int size;

	public MyArrayList(int capacity) {
		array = new Object[capacity];
	}

	public MyArrayList() {
		this(21);
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
			array[validIndex] = value;
		} else {
			Object[] lastElements = new Object[size - validIndex];
			for (int i = validIndex; i < size; i++) {
				lastElements[i - validIndex] = array[i];
			}
			array[validIndex++] = value;
			for (int i = 0; i < lastElements.length; i++) {
				array[i + validIndex] = lastElements[i];
			}
		}
		size++;
	}

	private void expandArray() {
		Object[] temp = array;
		int newLength = array.length + (array.length >> 1);
		array = new Object[newLength];
		for (int i = 0; i < temp.length; i++) {
			array[i] = temp[i];
		}
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
		int index = indexOf(t);
		if(index<0) {
			throw new NoSuchElementException(t.toString());
		}
		shiftLeft(index);
		return null;
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
			throw new ArrayIndexOutOfBoundsException();
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
