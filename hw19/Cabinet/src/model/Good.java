package model;

public abstract class Good {

	public abstract Long getId();

	public abstract String getType();

	public abstract double getPrice();

	public abstract String getDescription();

	public abstract String getImageUrl();
}