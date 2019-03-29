import java.util.Arrays;

import hw3.DistributedRoles;
import hw3.MergeUtils;

public class Test {
	public static void main(String[] args) {
		System.out.println(DistributedRoles
				.printTextPerRole(new String[] {"Johny","Pepper"}, new String[] {"Johny: hsk(*&^%$#?//||\\Pepper:jfdh8732987ig - hello","Pepper: what you say?"}));
		int[] a1 = new int[] {34,234,532};
		int[] a2 = new int[] {1,4,6,8};
		int[] merged = MergeUtils.mergeArrays(a1, a2);
		System.out.println(Arrays.toString(merged));
	}
}
