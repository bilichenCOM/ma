package db;

import java.util.List;
import java.util.Optional;

import model.Book;

public class BookCRUD implements CabinetCRUD<Book>{

	@Override
	public void create(Book book) {
		DBConnector.connect();
		DBConnector.addBook(book);
		DBConnector.disconnect();
	}

	@Override
	public Optional<Book> read(String id) {
		DBConnector.connect();
		Optional<Book> book = DBConnector.getBook(Long.parseLong(id));
		DBConnector.disconnect();
		return book;
	}
	
	public List<Book> readAll() {
		DBConnector.connect();
		List<Book> books = DBConnector.getBookList();
		DBConnector.disconnect();
		return books;
	}

	@Override
	public void update(Book book) {
		DBConnector.connect();
		DBConnector.updateBook(book);
		DBConnector.disconnect();
	}

	@Override
	public void delete(String id) {
		DBConnector.connect();
		DBConnector.deleteBook(Long.parseLong(id));
		DBConnector.disconnect();
	}
}