package db;

import java.util.List;
import java.util.Optional;

import model.Book;

public class BookCRUD implements CabinetCRUD<Book>{

	@Override
	public void create(Book book) throws ConnectionException {
		DBConnector.connect();
		DBConnector.addBook(book);
		DBConnector.disconnect();
	}

	@Override
	public Optional<Book> read(String id) throws ConnectionException {
		DBConnector.connect();
		Optional<Book> book = DBConnector.getBook(Long.parseLong(id));
		DBConnector.disconnect();
		return book;
	}
	
	public List<Book> readAll() throws ConnectionException {
		DBConnector.connect();
		List<Book> books = DBConnector.getBookList();
		DBConnector.disconnect();
		return books;
	}

	@Override
	public void update(Book t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String s) throws Exception {
		// TODO Auto-generated method stub
		
	}
}