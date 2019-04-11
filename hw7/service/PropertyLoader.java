package hw7.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PropertyLoader {

	public static String getProperty() throws IOException {
		Path property = Paths.get("application.properties");
		String propertyFileString = new String(Files.readAllBytes(property));
		String result = propertyFileString.split("=")[1];
		return result;
	}
}
