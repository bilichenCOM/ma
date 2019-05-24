package db;

import java.util.List;
import java.util.Optional;

import model.User;

public class UserCrud implements CabinetCrud<User> {

	@Override
	public void add(User user) throws ExistingUserException, ConnectionException {
		DbConnector.connect();
		DbConnector.addUser(user);
		DbConnector.disconnect();
	}

	@Override
	public Optional<User> read(Long id) throws WrongEmailException, ConnectionException {
		DbConnector.connect();
		Optional<User> optional = DbConnector.getUser(id);
		DbConnector.disconnect();
		return optional;
	}
	
	public Optional<User> read(String email) {
		DbConnector.connect();
		Optional<User> optional = DbConnector.getUser(email);
		DbConnector.disconnect();
		return optional;
	}

	@Override
	public void update(User user) {
		DbConnector.connect();
		DbConnector.updateUser(user);
		DbConnector.disconnect();
	}

	@Override
	public void delete(Long id) {
		DbConnector.connect();
		DbConnector.deleteUser(id);
		DbConnector.disconnect();
	}

	public List<User> readAll() {
		DbConnector.connect();
		List<User> userList = DbConnector.getUserList();
		DbConnector.disconnect();
		return userList;
	}
}