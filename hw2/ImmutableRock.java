package hw2;

import java.awt.Color;

public final class ImmutableRock {

	private final String material;
	private final Color color;
	private double weight;
	private double height;
	private double width;
	private boolean heavy;
	
	public ImmutableRock(String material, Color color, double weight, double height, double width) {
		super();
		this.material = material;
		this.color = color;
		this.weight = weight;
		this.height = height;
		this.width = width;
		if(weight>10000) this.heavy=true;
	}

	public String getMaterial() {
		return material;
	}

	public Color getColor() {
		return color;
	}

	public double getWeight() {
		return weight;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public boolean isHeavy() {
		return heavy;
	}
	
}
