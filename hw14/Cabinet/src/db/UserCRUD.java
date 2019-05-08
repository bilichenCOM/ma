package db;

import java.util.List;
import java.util.Optional;

import model.User;
import utils.ShaPasswordGenerator;

public class UserCRUD implements CabinetCRUD<User> {

	@Override
	public void create(User user) throws ExistingUserException, ConnectionException {
		hashUserPassword(user);
		DBConnector.connect();
		DBConnector.addUser(user);
		DBConnector.disconnect();
	}

	@Override
	public Optional<User> read(String email) throws WrongEmailException, ConnectionException {
		DBConnector.connect();
		Optional<User> optional = DBConnector.getUser(email);
		DBConnector.disconnect();
		return optional;
	}

	@Override
	public void update(User user) throws ConnectionException {
		DBConnector.connect();
		DBConnector.updateUser(user);
		DBConnector.disconnect();
	}

	@Override
	public void delete(String email) throws ConnectionException {
		DBConnector.connect();
		DBConnector.deleteUser(email);
		DBConnector.disconnect();
	}

	public List<User> readAll() {
		DBConnector.connect();
		List<User> userList = DBConnector.getUserList();
		DBConnector.disconnect();
		return userList;
	}
	
	private static void hashUserPassword(User user) {
		String salt = ShaPasswordGenerator.generateSalt();
		String password = ShaPasswordGenerator.getShaPassword(user.getPassword(), salt);
		user.setPassword(password);
		user.setSalt(salt);
	}
}