package hw7.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import hw7.di.Component;
import hw7.model.Creature;

@Component
public class FileCreaturetDao implements CreatureDao {

	public void save(Creature creature) {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("storage.dat"))) {
			objectOutputStream.writeObject(creature);
		} catch (IOException e) {
			System.out.println("Не удалось записать клиента в базу");
		}
	}

	public Creature get() {
		try (ObjectInputStream inputObjectStream = new ObjectInputStream(new FileInputStream("storage.dat"))) {
			return (Creature) inputObjectStream.readObject();
		} catch (Exception e) {
			System.out.println("Клиент не найден в системе");
			return null;
		}
	}
}
