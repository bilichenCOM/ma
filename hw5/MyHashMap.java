package hw5;

import java.util.NoSuchElementException;

public class MyHashMap<K, V> implements Map<K, V> {

	private Node<K, V>[] table;
	private int size;

	private final float loadFactor;

	private static final int DEFAULT_CAPACITY = 21;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;

	@SuppressWarnings("unchecked")
	public MyHashMap(int capacity, float loadFactor) {
		this.table = new Node[capacity];
		this.loadFactor = loadFactor;
	}

	public MyHashMap(int capacity) {
		this(capacity, DEFAULT_LOAD_FACTOR);
	}

	public MyHashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

// start of inner class Node
	private class Node<A, B> {
		private final A key;
		private B value;
		private Node<A, B> next;

		public Node(A key, B value) {
			this.key = key;
			this.value = value;
		}

		public A getKey() {
			return key;
		}

		public B getValue() {
			return value;
		}

		public void setValue(B value) {
			this.value = value;
		}

		public Node<A, B> getNext() {
			return next;
		}

		public boolean hasNext() {
			return this.next != null;
		}

		public void setNext(Node<A, B> next) {
			this.next = next;
		}
	} // end of inner class Node

	public void put(K key, V value) {
		if (size >= loadFactor * table.length) {
			expandTable();
		}

		putInBucket(index(key), key, value);
		size++;
	}

	public V remove(K key) {
		Node<K, V> indexEntry = table[index(key)];
		Node<K, V> matchedEntry = findMatches(indexEntry, key);
		if (matchedEntry == null) {
			throw new NoSuchElementException("Key not found: " + key);
		}

		Node<K, V> beforeMatchedEntry = findBeforeMatches(indexEntry, key);
		if (beforeMatchedEntry == null) {
			table[index(key)] = null;
		} else {
			beforeMatchedEntry.setNext(matchedEntry.getNext());
		}

		size--;
		return matchedEntry.getValue();
	}

	public void clear() {
		table = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public V get(K key) {
		if (findMatches(table[index(key)], key) == null) {
			throw new NoSuchElementException("Key not found: " + key);
		}
		return findMatches(table[index(key)], key).getValue();
	}

// +++++++++++++private methods
	private Node<K, V> findBeforeMatches(Node<K, V> indexEntry, K key) {
		if (!indexEntry.hasNext()) {
			return null;
		} else if (indexEntry.getNext().getKey().equals(key)) {
			return indexEntry;
		} else {
			return findBeforeMatches(indexEntry.getNext(), key);
		}
	}

	@SuppressWarnings("unchecked")
	private void expandTable() {
		Node<K, V>[] oldTable = table;
		int newCapacity = oldTable.length << 1;
		table = new Node[newCapacity];
		size = 0;
		rewriteFromTable(oldTable);
	}

	private void rewriteFromTable(Node<K, V>[] table) {
		for (Node<K, V> entry : table) {
			putFromNode(entry);
		}
	}

	private void putFromNode(Node<K, V> entry) {
		if (entry == null) {
			return;
		}
		put(entry.getKey(), entry.getValue());
		putFromNode(entry.getNext());
	}

	private void putInBucket(int index, K key, V value) {
		Node<K, V> currentEntry = new Node<>(key, value);
		if (table[index] == null) {
			table[index] = currentEntry;
			return;
		}

		Node<K, V> duplicateKeyEntry = findMatches(table[index], currentEntry.getKey());
		if (duplicateKeyEntry != null) {
			duplicateKeyEntry.setValue(value);
			size--;
			return;
		}

		Node<K, V> lastEntry = findLeaf(table[index]);
		lastEntry.setNext(currentEntry);

	}

	private Node<K, V> findLeaf(Node<K, V> existingEntry) {
		return (!existingEntry.hasNext()) ? existingEntry : findLeaf(existingEntry.getNext());
	}

	private Node<K, V> findMatches(Node<K, V> indexEntry, K currentKey) {
		if (indexEntry == null) {
			return null;
		} else if (indexEntry.getKey() == null && currentKey == null) {
			return indexEntry;
		} else if (indexEntry.getKey() != null && indexEntry.getKey().equals(currentKey)) {
			return indexEntry;
		} else if (indexEntry.hasNext()) {
			return findMatches(indexEntry.getNext(), currentKey);
		}

		return null;
	}

	private int index(K key) {
		return (key == null) ? 0 : key.hashCode() % table.length;
	}
}