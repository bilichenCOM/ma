import java.util.Arrays;

import hw2.SortUtils;

public class Test {
	public static void main(String[] args) {
		double[] array1 = new double[] {7623,5353,144,43.434,341,4356,211,-2,-433,73632,233.43};
		double[] merged = SortUtils.sortMerge(array1);
		System.out.println(Arrays.toString(merged));
	}
}
