package model;

import javax.persistence.Entity;

@Entity
public class Protein extends Good {

	private Long id;
	private String name;
	private String manufacturer;
	private Integer year;
	private String imageUrl;
	private Double price;

	public Protein() {}
	public Protein(Long id, String name, String manufacturer, Integer year, String imageUrl, Double price) {
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
		this.year = year;
		this.imageUrl = imageUrl;
		this.price = price;
	}

	public Protein(String name, String manufacturer, Integer year, String imageUrl, Double price) {
		this(null, name, manufacturer, year, imageUrl, price);
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getType() {
		return "Protein";
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public String getDescription() {
		return toString();
	}

	@Override
	public String getImageUrl() {
		return imageUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		Protein other = (Protein) obj;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Protein [id=" + id + ", name=" + name + ", manufacturer=" + manufacturer + ", year=" + year + ", price="
				+ price + "]";
	}
}