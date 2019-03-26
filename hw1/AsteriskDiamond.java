package hw1;

public class AsteriskDiamond {

	private static final String ASTERISK = "*";
	private static final String SPACE = " ";

	public static void printDiamond(int diamondSize) {
		String axis = getAxis(diamondSize);

		for (int k = 0; k < diamondSize; k++) {
			StringBuilder line = new StringBuilder();

			StringBuilder spaces = new StringBuilder();
			StringBuilder asterisks = new StringBuilder();

			int spacesCount = Math.abs(diamondSize / 2 - k);
			int asterisksCount = diamondSize / 2 - spacesCount;

			for (int i = 0; i < spacesCount; i++) {
				spaces.append(SPACE);
			}

			for (int j = 0; j < asterisksCount; j++) {
				asterisks.append(ASTERISK);
			}

			line.append(spaces);
			line.append(asterisks);
			line.append(axis);
			line.append(asterisks);
			System.out.println(line);
		}
	}

	private static String getAxis(int size) {
		return (size % 2 == 0) ? "" : ASTERISK;
	}
}
