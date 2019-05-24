package db;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {

	void add(T t);

	Optional<T> read(Class<T> clazz, Long id);

	void update(T t);

	void delete(Class<T> clazz, Long id);

	List<T> readAll(Class<T> clazz);
}
