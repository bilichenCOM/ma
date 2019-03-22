
public class AsteriskDiamond {

	public static final String ASTERISK = "*";
	public static final String SPACE = " ";
	
	public static void printDiamond(int size) {
		String axis = getAxis(size);
		
		for(int k=0; k<size; k++) {
			StringBuilder line = new StringBuilder();
			
			StringBuilder spaces = new StringBuilder();
			StringBuilder asterisks = new StringBuilder();
			
			int sp_count = Math.abs(size/2-k);
			int ast_count = size/2-sp_count;
			
			for(int i = 0; i<sp_count; i++) {
				spaces.append(SPACE);
			}
			for(int j = 0; j<ast_count; j++) {
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
		return (size%2==0)?"":ASTERISK;
	}
}
