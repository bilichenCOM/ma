package db;

import java.util.List;

import model.Purchase;

public interface PurchaseDao extends AdoptedDao<Purchase> {

	List<Purchase> getBasketPurchases(Long userId);
}