package hw1;

public class AsteriskDiamond {

<<<<<<< HEAD
	public static final String ASTERISK = "*";
	public static final String SPACE = " ";

	public static void printDiamond(int size) {
		String axis = getAxis(size);

		for (int k = 0; k < size; k++) {
=======
	private static final String ASTERISK = "*";
	private static final String SPACE = " ";

	public static void printDiamond(int diamondSize) {
		String axis = getAxis(diamondSize);

		for (int k = 0; k < diamondSize; k++) {
>>>>>>> 0e9ea38... corrections2
			StringBuilder line = new StringBuilder();

			StringBuilder spaces = new StringBuilder();
			StringBuilder asterisks = new StringBuilder();

<<<<<<< HEAD
			int sp_count = Math.abs(size / 2 - k);
			int ast_count = size / 2 - sp_count;

			for (int i = 0; i < sp_count; i++) {
				spaces.append(SPACE);
			}

			for (int j = 0; j < ast_count; j++) {
=======
			int spacesCount = Math.abs(diamondSize / 2 - k);
			int asterisksCount = diamondSize / 2 - spacesCount;

			for (int i = 0; i < spacesCount; i++) {
				spaces.append(SPACE);
			}

			for (int j = 0; j < asterisksCount; j++) {
>>>>>>> 0e9ea38... corrections2
				asterisks.append(ASTERISK);
			}

			line.append(spaces);
			line.append(asterisks);
			line.append(axis);
			line.append(asterisks);
			System.out.println(line);
		}
<<<<<<< HEAD

=======
>>>>>>> 0e9ea38... corrections2
	}

	private static String getAxis(int size) {
		return (size % 2 == 0) ? "" : ASTERISK;
	}

}
