package hw7.dao;

import java.util.List;

import hw7.model.Creature;

public interface CreatureDao {
	void save(Creature creature);

	List<Creature> get() throws Exception;
}
