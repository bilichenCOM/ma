package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Cards")
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@ManyToMany
	@JoinTable(name = "card_good",
			joinColumns = @JoinColumn(name = "card_id"),
			inverseJoinColumns = @JoinColumn(name = "good_id"))
	private List<Good> goods;
	private int size;
	private double value;

	public Card() {
		this.goods = new ArrayList<>();
	}

	public Card(Long id, Long userId, List<Good> goods) {
		this.id = id;
		this.userId = userId;
		this.goods = goods;
	}

	public Card(Long userId, List<Good> goods) {
		this(null, userId, goods);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Good> getGoods() {
		return goods;
	}

	public void addGood(Good good) {
		size++;
		value += good.getPrice();
		goods.add(good);
	}

	public int size() {
		return goods.size();
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getSize() {
		return size;
	}

	public void removeGood(Good good) {
		goods.remove(good);
		size--;
		value -= good.getPrice();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goods == null) ? 0 : goods.hashCode());
		result = prime * result + size;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (goods == null) {
			if (other.goods != null)
				return false;
		} else if (!goods.equals(other.goods))
			return false;
		if (size != other.size)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", userId=" + userId + ", goods=" + goods + ", size=" + size + ", value=" + value
				+ "]";
	}
}