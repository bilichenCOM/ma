package hw5;

public interface Map<K, V> {
	void put(K key, V value);

	V remove(K key);

	void clear();

	int size();

	V get(K key);
}