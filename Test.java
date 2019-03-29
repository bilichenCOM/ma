import java.util.Arrays;

import hw3.DistributedRoles;
import hw3.MergeUtils;

public class Test {
	public static void main(String[] args) {
		String[] roles = new String[] { "Johny", "fooBar", "pipka", "cat" };
		String[] textLines = new String[] {"Johny: hello cat: im Johny","cat: who you are?", "djlaj8wiwjf", "fooBar: hello im foobar", "pipka: what is going on?"};
		System.out.println(DistributedRoles.printTextPerRole(roles, textLines));
		int[] array1 = new int[] {3,249,443,2341,23243};
		int[] array2 = new int[] {3,4,42114,4424242};
		int[] merged = MergeUtils.mergeArrays(array1, array2);
		System.out.println(Arrays.toString(array1)+"\n"+Arrays.toString(array2)+"\n"+Arrays.toString(merged));
	}
}
