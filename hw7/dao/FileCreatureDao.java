package hw7.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import hw7.di.Component;
import hw7.model.Creature;

@Component
public class FileCreatureDao implements CreatureDao {
	private static final String DB_NAME = "storage";
	private static final String DB_TYPE = ".dat";

	public void save(Creature creature) {
		String fileName = DB_NAME + timeStamp() + DB_TYPE;
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
			outputStream.writeObject(creature);
		} catch (FileNotFoundException e) {
			System.out.printf("File %s is unavailable!", fileName);
		} catch (IOException e) {
			System.out.println("Error by saving to database");
		}
	}

	public List<Creature> get() {
		List<Creature> list = new ArrayList<Creature>();
		List<String> dbFileNames = getFileNamesList();
		for (String dbFileName : dbFileNames) {
			try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(dbFileName))) {
				list.add((Creature) inputStream.readObject());
			} catch (FileNotFoundException e) {
				System.out.printf("DB File %s not exist or was damaged!", dbFileName);
			} catch (ClassNotFoundException e) {
				System.out.printf("%s - not correct db file!", dbFileName);
			} catch (IOException e) {
				System.out.printf("Error reading file %s", dbFileName);
			}
		}
		return list;
	}

	private List<String> getFileNamesList() {
		List<String> dbFileNames = new ArrayList<>();
		try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(Paths.get(""), "*" + DB_TYPE)) {
			dirStream.forEach(f -> dbFileNames.add(f.getFileName().toString()));
		} catch (IOException e) {
			System.err.printf("Error during retrieving file list!");
		}
		return dbFileNames;
	}

	private long timeStamp() {
		return System.currentTimeMillis();
	}
}