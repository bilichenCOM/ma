import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import hw2.BookItem;
import hw2.BookItem.BookItemBuilder;
import hw2.Group;
import hw2.Service;
import hw2.SortUtils;

public class Test {

	public static void main(String[] args) {

		BookItem.BookItemBuilder abstractBookBuilder = new BookItemBuilder()
				.withTitle("How To Take Galaxy By Storm")
				.writtenBy("Tkugma")
				.publishedOn(2019)
				.withPageNumber(243)
				.fromSection("RealFantastic")
				.sizeOfStock(1000);
		BookItem abstractBook = abstractBookBuilder.build();
		BookItem nonAbstractBook = abstractBookBuilder
				.withTitle("History Of Survival Of Our Kind")
				.publishedOn(2092)
				.withPageNumber(387)
				.build();
		Set<BookItem> set = new TreeSet<>(new Comparator<BookItem>() {
			public int compare(BookItem o1, BookItem o2) {
				return o1.getYear()-o1.getYear();
			}
		});
		set.add(abstractBook);
		set.add(nonAbstractBook);
		System.out.println(set.toString());

		System.out.println(abstractBook);

		double[] arr = new double[] { 1, 3, -4, 3.323, 4343, 5, 77.7, -0.87 };
		System.out.println("Before: "+Arrays.toString(arr)+"\r\napllying buble sorting\r\nAfter: "+Arrays.toString(SortUtils.sortBuble(arr)));
		arr = new double[] { 3, -2, 3, 4, -1, 9, 32, -3, 45, -9, 7 };
		System.out.println("Before: "+Arrays.toString(arr)+"\r\napllying merge sorting\r\nAfter: "+Arrays.toString(SortUtils.sortMerge(arr)));
//		
		Group group = new Group();
		group.setTriangle(new Group.Triangle("t1"));
		group.setCircle(new Group.Circle("c1"));
		group.setSquare(new Group.Square("s1"));
		Group child = new Group(new Group.Triangle("t2"), new Group.Circle("c2"), new Group.Square("s2"));
		group.addChild(child);
		System.out.println(group.getChild());

		Service ser = new Service(new File("group.txt"));
		ser.serializeGroup(group);
	}
}
