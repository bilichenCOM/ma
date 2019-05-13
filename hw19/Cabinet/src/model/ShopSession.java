package model;

import java.util.List;

public class ShopSession {

	private User user;
	private List<? extends Good> goods;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<? extends Good> getGoods() {
		return goods;
	}

	public void setGoods(List<? extends Good> goods) {
		this.goods = goods;
	}	
}