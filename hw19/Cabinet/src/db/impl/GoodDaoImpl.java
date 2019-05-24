package db.impl;

import java.util.List;
import java.util.Optional;

import db.GoodDao;
import model.Good;

public class GoodDaoImpl extends GenericDaoImpl<Good> implements GoodDao{

	@Override
	public Optional<Good> read(Long id) {
		return read(Good.class, id);
	}

	@Override
	public List<Good> readAll() {
		return readAll(Good.class);
	}


	@Override
	public void delete(Long id) {
		delete(Good.class, id);
	}

}
