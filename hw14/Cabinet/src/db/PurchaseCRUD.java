package db;

import java.util.Optional;

import model.Purchase;

public class PurchaseCRUD implements CabinetCRUD<Purchase> {

	@Override
	public void create(Purchase purchase) {
		DBConnector.connect();
		DBConnector.addPurchase(purchase);
		DBConnector.disconnect();
	}

	@Override
	public Optional<Purchase> read(String s) {
		DBConnector.connect();
		Optional<Purchase> purchase = DBConnector.getPurchase(Long.parseLong(s));
		DBConnector.disconnect();
		return purchase;
	}

	@Override
	public void update(Purchase t) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(String s) throws Exception {
		// TODO Auto-generated method stub
	}
}