	public static String printTextPerRole(String[] roles, String[] textLines) {
		StringBuilder text = new StringBuilder();
		for(int i = 0; i<roles.length; i++) {
            text.append(roles[i]+":\n");
            for(int j = 0; j<textLines.length; j++) {
                if(textLines[j].contains(roles[i])) {
                    text.append((j+1)+") ")
                        .append(textLines[j].replace(roles[i]+": ",""))
                        .append("\n");
                }
            }
            text.append("\n");
        }
		return text.toString();
	}