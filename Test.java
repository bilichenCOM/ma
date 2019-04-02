import hw4.List;
import hw4.MyArrayList;
import hw4.MyLinkedList;

public class Test {
	public static void main(String[] args) {
		List<String> linkedList = new MyLinkedList<String>();
		addTestValues(linkedList, 21);
		printValues(linkedList);

		List<String> arrayList = new MyArrayList<String>();
		addTestValues(arrayList, 34);
		printValues(arrayList);

		linkedList.addAll(arrayList);
		printValues(linkedList);
		arrayList.addAll(linkedList);
		printValues(arrayList);
	}

	private static void addTestValues(List<String> list, int valuesNumber) {
		for (int index = 0; index < valuesNumber; index++) {
			list.add("value number" + index);
		}

		list.add("begin", 0);
		list.add("end", list.size());
		list.add("random", (int) (Math.random() * list.size()));
		list.add("center", list.size() / 2);

	}

	private static void printValues(List<?> list) {
		System.out.println("Values in list:");
		for (int index = 0; index < list.size(); index++) {
			System.out.println(list.get(index));
		}

		System.out.println("/////////////////////////");
	}
}