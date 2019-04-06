package hw6;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class AnimalDecoder {
	public static Animal[] deserializeAnimalArray(byte[] data) {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		Animal[] objDeserial = null;

		try (ObjectInputStream ois = new ObjectInputStream(bais)) {
			int objCount = ois.readInt();
			objDeserial = new Animal[objCount];
			for (int objNumber = 0; objNumber < objCount; objNumber++) {
				objDeserial[objNumber] = (Animal) ois.readObject();
			}
		} catch (Exception ex) {
			throw new IllegalArgumentException("Something went wrong during the deserialization");
		}

		return objDeserial;
	}
}