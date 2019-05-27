package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Goods")
public abstract class Good {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public abstract Long getId();

	public abstract String getType();

	public abstract double getPrice();

	public abstract String getDescription();

	public abstract String getImageUrl();
}