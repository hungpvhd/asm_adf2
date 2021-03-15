package adf2.service;

import adf2.entity.Book;

import java.util.ArrayList;

public interface BookService {

    void save(Book book);

    ArrayList<Book> loadList();

    Book getByName(String name);
    
    void deleteBook(int id);
    
    Book getBookById(int id);

    void saveEdit(Book rs);
}
