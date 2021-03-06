package hw2;

public class BookItem {

	private final String title;
	private final String author;
	private final int year;
	private final int pageNumber;
	private final String section;
	private int price;
	private int stock;

	private BookItem(BookItemBuilder builder) {
		this.title = builder.title;
		this.author = builder.author;
		this.year = builder.year;
		this.pageNumber = builder.pageNumber;
		this.section = builder.section;
		this.stock = builder.stock;
		this.price = builder.price;
	}
// start of inner class
	public static class BookItemBuilder {
		private String title;
		private String author;
		private int year;
		private int pageNumber;
		private String section;
		private int price;
		private int stock;

		public BookItemBuilder withTitle(String title) {
			this.title = title;
			return this;
		}

		public BookItemBuilder writtenBy(String author) {
			this.author = author;
			return this;
		}

		public BookItemBuilder publishedOn(int year) {
			this.year = year;
			return this;
		}

		public BookItemBuilder withPageNumber(int pageNumber) {
			this.pageNumber = pageNumber;
			return this;
		}

		public BookItemBuilder fromSection(String section) {
			this.section = section;
			return this;
		}

		public BookItemBuilder withPrice(int price) {
			this.price = price;
			return this;
		}

		public BookItemBuilder sizeOfStock(int stock) {
			this.stock = stock;
			return this;
		}

		public BookItem build() {
			return new BookItem(this);
		}
	}
//	end of inner class
	@Override
	public String toString() {
		return "BookItem [title=" + title + ", author=" + author + ", year=" + year + ", pageNumber=" + pageNumber
				+ ", section=" + section + ", price=" + price + ", stock=" + stock + "]";
	}
}