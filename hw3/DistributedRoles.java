package hw3;

public class DistributedRoles {
	public static String printTextPerRole(String[] roles, String[] textLines) {
		StringBuilder text = new StringBuilder();
		for (int i = 0; i < roles.length; i++) {
			text.append(roles[i] + ":\n");
			for (int j = 0; j < textLines.length; j++) {
				text.append(checkAndWrite(roles[i], textLines[j], j+1, ":"));
			}
			text.append("\n");
		}
		return text.toString();
	}
	
	private static StringBuilder checkAndWrite(String role, String textLine, int number, String delimiter) {
		StringBuilder text = new StringBuilder();
		if (textLine.startsWith(role+delimiter)) {
			text.append(number + ") ")
			.append(textLine.replaceFirst(role+delimiter, "").trim())
			.append("\n");
		}
		return text;
	}
}