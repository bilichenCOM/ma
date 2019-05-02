package model;

public enum Role {
	ADMIN(1),
	USER(2);

	private int id;

	private Role(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}