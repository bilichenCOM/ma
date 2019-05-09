package db;

import java.util.List;
import java.util.Optional;

import model.User;
import utils.ShaPasswordGenerator;

public class UserCrud implements CabinetCrud<User> {

	@Override
	public void create(User user) throws ExistingUserException, ConnectionException {
		hashUserPassword(user);
		DbConnector.connect();
		DbConnector.addUser(user);
		DbConnector.disconnect();
	}

	@Override
	public Optional<User> read(String email) throws WrongEmailException, ConnectionException {
		DbConnector.connect();
		Optional<User> optional = DbConnector.getUser(email);
		DbConnector.disconnect();
		return optional;
	}

	@Override
	public void update(User user) throws ConnectionException {
		DbConnector.connect();
		DbConnector.updateUser(user);
		DbConnector.disconnect();
	}

	@Override
	public void delete(String email) throws ConnectionException {
		DbConnector.connect();
		DbConnector.deleteUser(email);
		DbConnector.disconnect();
	}

	public List<User> readAll() {
		DbConnector.connect();
		List<User> userList = DbConnector.getUserList();
		DbConnector.disconnect();
		return userList;
	}
	
	private static void hashUserPassword(User user) {
		String salt = ShaPasswordGenerator.generateSalt();
		String password = ShaPasswordGenerator.getShaPassword(user.getPassword(), salt);
		user.setPassword(password);
		user.setSalt(salt);
	}
}