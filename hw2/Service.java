package hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Service{
	private StringBuilder output;
	private File file;
	
	public Service(File file) {
		this.file = file;
		output = new StringBuilder();
	}

	public <T extends Group> void serializeGroup(T group) {
		output.append("object:"+group+"{\r\n");
		output.append("variable: triangle=");
		if(group.getTriangle()!=null) {
			output.append(group.getTriangle()+"{\r\n");
			output.append("\tvariable: name="+group.getTriangle().getName()+"\r\n}\r\n");
		} else {
			output.append("null\r\n");
		}
		output.append("variable: circle=");
		if(group.getCircle()!=null) {
			output.append(group.getCircle()+"{\r\n");
			output.append("\tvariable: name="+group.getCircle().getName()+"\r\n}\r\n");
		} else {
			output.append("null\r\n");
		}
		output.append("variable: square=");
		if(group.getSquare()!=null) {
			output.append(group.getSquare()+"{\r\n");
			output.append("\tvariable: name="+group.getSquare().getName()+"\r\n}\r\n");
		} else {
			output.append("null\r\n");
		}
		output.append("variable: group=");
		if(group.getChild()!=null) {
			output.append(group.getChild()+"\r\n}\r\n");
			serializeGroup(group.getChild());
		} else {
			output.append("null\r\n}");
			writeToFile(output);
		}
	}
	
	private void writeToFile(StringBuilder output) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			osw.write(output.toString());
			osw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
