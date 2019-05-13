package db;

import java.util.List;
import java.util.Optional;

import model.Book;

public class BookCrud implements CabinetCrud<Book>{

	@Override
	public void add(Book book) {
		DbConnector.connect();
		DbConnector.addBook(book);
		DbConnector.disconnect();
	}

	@Override
	public Optional<Book> read(Long id) {
		DbConnector.connect();
		Optional<Book> book = DbConnector.getBook(id);
		DbConnector.disconnect();
		return book;
	}
	
	public List<Book> readAll() {
		DbConnector.connect();
		List<Book> books = DbConnector.getBookList();
		DbConnector.disconnect();
		return books;
	}

	@Override
	public void update(Book book) {
		DbConnector.connect();
		DbConnector.updateBook(book);
		DbConnector.disconnect();
	}

	@Override
	public void delete(Long id) {
		DbConnector.connect();
		DbConnector.deleteBook(id);
		DbConnector.disconnect();
	}
}