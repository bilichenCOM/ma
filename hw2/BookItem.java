package hw2;

public class BookItem {

	private final String title;
	private final String author;
	private final int year;
	private final int pageNumber;
	private final String section;
	private int price;
	private int stock;

<<<<<<< HEAD
	private BookItem(BookItemBuilder builder) {
=======
	public BookItem(BookItemBuilder builder) {
		super();
>>>>>>> c77268321dad0543219877de7b60fd293a93fa30
		this.title = builder.title;
		this.author = builder.author;
		this.year = builder.year;
		this.pageNumber = builder.pageNumber;
		this.section = builder.section;
		this.stock = builder.stock;
		this.price = builder.price;
	}

<<<<<<< HEAD
// start of inner class
=======
//	start of inner class
>>>>>>> c77268321dad0543219877de7b60fd293a93fa30
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

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getYear() {
		return year;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public String getSection() {
		return section;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return this.title + ", " + this.author + ", " + this.year + ", " + this.section;
	}
<<<<<<< HEAD
=======

>>>>>>> c77268321dad0543219877de7b60fd293a93fa30
}