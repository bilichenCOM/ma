package db;

import java.util.List;
import java.util.Optional;

public interface CabinetCrud<T> {

	void add(T t) throws Exception;

	Optional<T> read(Long id) throws Exception;

	void update(T t) throws Exception;

	void delete(Long id) throws Exception;
	
	List<T> readAll();
}