package hw7.dao;

import hw7.di.Component;
import hw7.model.Creature;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryCreatureDao implements CreatureDao {

	private static final List<Creature> inMemoryStorage = new ArrayList<>();

	@Override
	public void save(Creature client) {
		inMemoryStorage.add(client);
	}

	@Override
	public List<Creature> get() {
		return inMemoryStorage;
	}
}