package db;

import java.util.List;
import java.util.Optional;

import model.Purchase;

public class PurchaseCrud implements CabinetCrud<Purchase> {

	@Override
	public void add(Purchase purchase) {
		DbConnector.connect();
		DbConnector.addPurchase(purchase);
		DbConnector.disconnect();
	}

	@Override
	public Optional<Purchase> read(Long id) {
		DbConnector.connect();
		Optional<Purchase> purchase = DbConnector.getPurchase(id);
		DbConnector.disconnect();
		return purchase;
	}

	@Override
	public void update(Purchase t) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Purchase> readAll() {
		return null;
	}
}