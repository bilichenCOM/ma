package hw7.dao;

import hw7.model.Creature;

public interface CreatureDao {
	void save(Creature creature);

	Creature get();
}
