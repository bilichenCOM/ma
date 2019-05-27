package db;

import java.util.Optional;

import model.User;

public interface UserDao extends AdoptedDao<User>{

	Optional<User> readByEmail(String email);
}
