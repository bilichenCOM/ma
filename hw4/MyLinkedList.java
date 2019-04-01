package hw4;

public class MyLinkedList<E> implements List<E> {

	private Node<E> firstNode;
	private Node<E> lastNode;
	private int size;

	public MyLinkedList() {
	}

// start of inner class Node	

	private class Node<T> {
		private Node<T> previous;
		private Node<T> next;
		private T value;

		public Node(T value) {
			this.value = value;
		}

		public Node<T> getPrevious() {
			return previous;
		}

		public void setPrevious(Node<T> previous) {
			this.previous = previous;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public T getValue() {
			return value;
		}
	}
// end of inner class Node

	public void add(E value) {
		add(value, size);
	}

	public void add(E value, int index) {
		Node<E> currentNode = new Node<>(value);
		if (firstNode == null && index == 0) {
			firstNode = currentNode;
			bind(firstNode, lastNode);
			lastNode = firstNode;
			size++;
			return;
		}

		if (index == 0) {
			bind(currentNode, firstNode);
			firstNode = currentNode;
			size++;
			return;
		}

		if (index == size) {
			bind(lastNode, currentNode);
			lastNode = currentNode;
			size++;
			return;
		}

		Node<E> indexNode = getNode(index);
		Node<E> prevIndexNode = indexNode.getPrevious();
		bind(prevIndexNode, currentNode);
		bind(currentNode, indexNode);
		size++;
	}

	public void addAll(List<E> list) {
		if (list == this) {
			throw new IllegalArgumentException("the same list cannot be duplicated");
		}

		for (int listIndex = 0; listIndex < list.size(); listIndex++) {
			add(list.get(listIndex));
		}
	}

	public E get(int index) {
		return getNode(index).getValue();
	}

	public void set(E value, int index) {
		getNode(index).setValue(value);
	}

	public E remove(int index) {
		Node<E> removedNode;
		if (index == 0) {
			removedNode = firstNode;
			firstNode = firstNode.getNext();
			bind(null, firstNode);
			size--;
		} else if (index == size - 1) {
			removedNode = lastNode;
			lastNode = lastNode.getPrevious();
			bind(lastNode, null);
			size--;
		} else {
			removedNode = getNode(index);
			bind(removedNode.getPrevious(), removedNode.getNext());
			size--;
		}
		return removedNode.getValue();
	}

	public E remove(E value) {
		remove(indexOf(value));
		return value;
	}

	private int indexOf(E value) {
		for (int index = 0; index < size; index++) {
			if (getNode(index).getValue().equals(value)) {
				return index;
			}
		}
		return -1;
	}

	private Node<E> getNode(int index) {
		checkIndex(index);
		if (index <= size / 2) {
			return stepToNode(firstNode, index);
		} else {
			return stepToNode(lastNode, index - size + 1);
		}
	}

	private Node<E> stepToNode(Node<E> startNode, int steps) {
		if (steps == 0) {
			return startNode;
		} else if (steps < 0) {
			steps++;
			return stepToNode(startNode.getPrevious(), steps);
		} else {
			steps--;
			return stepToNode(startNode.getNext(), steps);
		}
	}

	private void checkIndex(int index) {
		if (index < 0 || index > size - 1) {
			throw new IllegalArgumentException("not valid index: " + index + " for list size:" + size);
		}
	}

	private void bind(Node<E> node1, Node<E> node2) {
		if (node1 == null && node2 == null) {
			return;
		} else if (node1 == null) {
			node2.setPrevious(null);
			return;
		} else if (node2 == null) {
			node1.setNext(null);
			return;
		}
		node1.setNext(node2);
		node2.setPrevious(node1);
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
}