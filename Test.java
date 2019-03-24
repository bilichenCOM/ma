import java.io.File;
import java.util.Arrays;

import hw2.BookItem;
import hw2.BookItem.BookItemBuilder;
import hw2.Group;
import hw2.Service;
import hw2.SortUtils;

public class Test {

	public static void main(String[] args) {
		
		BookItem abstractBook = new BookItemBuilder()
				.withTitle("How To Take Galaxy By Storm")
				.writtenBy("Tkugma")
				.publishedOn(2019)
				.withPageNumber(243)
				.fromSection("RealFantastic")
				.sizeOfStock(1000)
				.build();
		
		System.out.println(abstractBook);
		
		double[] arr = SortUtils.sortBuble(new double[] {1,3,-4,3.323,4343,5,77.7,-0.87});
		System.out.println(Arrays.toString(arr));
		arr = SortUtils.sortShaker(new double[] {1,3,-4,-3.323,48343, 333.44, 4.4, 55,7,5,77.7,-0.87});
		System.out.println(Arrays.toString(arr));
		arr = SortUtils.sortComb(new double[] {1,3,4,3.323,3,-34, 333.44, 4.4, 55,7,5,77.7,-0.87});
		System.out.println(Arrays.toString(arr));
		arr = SortUtils.sortInsert(new double[] {782,324,-43,342,121.123,4356,4, 333.44, 4.4, 55,7,5,77.7,-0.87});
		System.out.println(Arrays.toString(arr));
		arr = SortUtils.sortSelection(new double[] {1,3,-3,23-32,5,546,23,323,3,-34, 333.44, 4.4, 55,7,5,77.7,-0.87});
		System.out.println(Arrays.toString(arr));
		arr = SortUtils.sortMerge(new double[] {3,-2,3,4,-1,9,32,-3,45,-9,7});
		System.out.println(Arrays.toString(arr));
//		
		Group group = new Group();
		group.setTriangle(new Group.Triangle("t1"));
		group.setCircle(new Group.Circle("c1"));
		group.setSquare(new Group.Square("s1"));
		Group child = new Group(new Group.Triangle("t2"),new Group.Circle("c2"),new Group.Square("s2"));
		group.addChild(child);
		System.out.println(group.getChild());
		
		Service ser = new Service(new File("group.txt"));
		ser.serializeGroup(group);
	}

}
