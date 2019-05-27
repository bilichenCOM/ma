package model;

public enum PurchaseStatus {

	FINISHED(1),
	UNFINISHED(2);

	private Integer id;

	private PurchaseStatus(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}