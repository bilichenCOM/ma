package db;

import java.util.Optional;

public interface CabinetCRUD<T> {

	void create(T t) throws Exception;

	Optional<T> read(String s) throws Exception;

	void update(T t) throws Exception;

	void delete(String s) throws Exception;
}