package hw7.model;

import java.io.Serializable;

public abstract class Creature implements Serializable {
	private static final long serialVersionUID = 1L;

	abstract String getName();
}