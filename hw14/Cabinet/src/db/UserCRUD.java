package db;

import java.util.Optional;

import model.User;

public class UserCRUD implements CabinetCRUD<User> {
	private static final UserCRUD instance = new UserCRUD();

	private UserCRUD() {
	}

	public static UserCRUD getInstance() {
		return instance;
	}

	@Override
	public void create(User user) throws ExistingUserException, ConnectionException {
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
}