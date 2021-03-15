package adf2.controller;

import adf2.entity.Book;
import adf2.service.BookService;
import adf2.service.BookServiceImpl;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class BookController {
    
    private final Logger log = Logger.getLogger(BookController.class.getName());
    private BookService bookService = new BookServiceImpl();
    private Scanner scanner = new Scanner(System.in);

    public void createBook() {
        log.info("Vui lòng nhập tiêu đề sách: ");
        String title = scanner.nextLine();
        log.info("Vui lòng nhập tác giả sách: ");
        String author = scanner.nextLine();
        log.info("Vui lòng nhập ngày phát hành sách: ");
        String releaseDate = scanner.nextLine();
        log.info("Vui lòng nhập nội dung sách: ");
        String content = scanner.nextLine();
        Book book = new Book(title, author, releaseDate, content);
        try {
            bookService.save(book);
            log.info("Lưu thành công!");
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
    }
    
    public ArrayList<Book> getList() {
        return bookService.loadList();
    }
    
    public Book getBookByTitle() {
        log.info("Vui lòng nhập tên sách: ");
        String title = scanner.nextLine();
        return bookService.getByName(title);
    }
    
    public Book getBookById() {
        log.info("Vui lòng nhập mã sách: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (bookService.getBookById(id) == null) {
            log.severe("Sản phẩm không tồn tại hoặc đã bị xoá.");
        } 
        return bookService.getBookById(id);
    }
    
    public void updateBook() {
        log.info("Vui lòng nhập mã sách: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (bookService.getBookById(id) == null) {
            log.severe("Sản phẩm không tồn tại hoặc đã bị xoá.");
        } else {
            Book book = bookService.getBookById(id);
            bookService.saveEdit(book);
        }
    }
    
    public void deleteBook() {
        log.info("Vui lòng nhập mã sách: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (bookService.getBookById(id) == null) log.severe("Sản phẩm không tồn tại hoặc đã bị xoá.");
        bookService.deleteBook(id);
    }
}
