package db;

import java.util.List;
import java.util.Optional;

public interface AdoptedDao<T> extends GenericDao<T> {

	Optional<T> read(Long id);

	List<T> readAll();

	void delete(Long id);

}
