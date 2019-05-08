package db;

import java.util.Optional;

import model.Purchase;

public class PurchaseCrud implements CabinetCrud<Purchase> {

	@Override
	public void create(Purchase purchase) {
		DbConnector.connect();
		DbConnector.addPurchase(purchase);
		DbConnector.disconnect();
	}

	@Override
	public Optional<Purchase> read(String s) {
		DbConnector.connect();
		Optional<Purchase> purchase = DbConnector.getPurchase(Long.parseLong(s));
		DbConnector.disconnect();
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