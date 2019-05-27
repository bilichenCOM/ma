package db.impl;

import java.util.List;
import java.util.Optional;

import db.CardDao;
import model.Card;

public class CardDaoImpl extends GenericDaoImpl<Card> implements CardDao {

	@Override
	public Optional<Card> read(Long id) {
		return read(Card.class, id);
	}

	@Override
	public List<Card> readAll() {
		return readAll(Card.class);
	}

	@Override
	public void delete(Long id) {
		delete(Card.class, id);
	}

}
