package adf2.service;

import adf2.entity.Book;
import adf2.helper.ConnectionHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class BookServiceImpl implements BookService {
    
    private final Logger log = Logger.getAnonymousLogger();

    private Scanner input = new Scanner(System.in);
    
    public void save(Book book) {
        try {
            PreparedStatement preparedStatement = ConnectionHelper.getConnection()
                    .prepareStatement("insert into books (title, author, releaseDate, content) values (?,?,?,?)");
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getReleaseDate());
            preparedStatement.setString(4, book.getContent());
            preparedStatement.execute();
        } catch (Exception ex) {
            log.info("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public ArrayList<Book> loadList() {
        ArrayList<Book> list = new ArrayList<>();
        try {
            String cmd = "select * from books";
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(cmd);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String releaseDate = resultSet.getString("releaseDate");
                String content = resultSet.getString("releaseDate");
                Book obj = new Book(title, author, releaseDate, content);
                list.add(obj);
            }
        } catch (Exception ex) {
            log.severe("Không thể lấy dữ liệu từ database. Message: " + ex.getMessage());
        }
        return list;
    }

    public Book getByName(String name) {
        try {
            String cmd = "select * from books where title = ?";
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(cmd);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String releaseDate = resultSet.getString("releaseDate");
                String content = resultSet.getString("releaseDate");
                return new Book(title, author, releaseDate, content);
            }
        } catch (Exception ex) {
            log.severe("Không thể lấy dữ liệu từ database. Message: " + ex.getMessage());
        }
        return null;
    }

    public void deleteBook(int id) {
        try {
            String query = "DELETE FROM books WHERE id = ?";
            PreparedStatement ps = ConnectionHelper.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ex) {
            log.severe(ex.getMessage());
        }
    }

    public Book getBookById(int id) {
        try {
            String cmd = "select * from books where id = ?";
            PreparedStatement preparedStatement = ConnectionHelper.getConnection().prepareStatement(cmd);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String releaseDate = resultSet.getString("releaseDate");
                String content = resultSet.getString("releaseDate");
                return new Book(title, author, releaseDate, content);
            }
        } catch (Exception ex) {
            log.severe("Không thể lấy dữ liệu từ database. Message: " + ex.getMessage());
        }
        return null;
    }

    public void saveEdit(Book rs) {
        log.info("Mã sách: " + rs.getId() + " - Tên sách: " + rs.getTitle() + " - Tác giả: " + rs.getAuthor());
        log.info("Vui lòng nhập tên mới của sách: ");
        String title = input.nextLine();
        log.info("Vui lòng nhập tác giả mới của sách: ");
        String author = input.nextLine();
        log.info("Vui lòng nhập ngày phát hành mới của sách: ");
        String releaseDate = input.nextLine();
        log.info("Vui lòng nhập nội dung mới của sách: ");
        String content = input.nextLine();
        try {
            String query = "UPDATE books SET title = ?, author = ?, releaseDate = ?, content = ? WHERE id = ?";
            PreparedStatement psm = ConnectionHelper.getConnection().prepareStatement(query);
            psm.setString(1, title);
            psm.setString(2, author);
            psm.setString(3, releaseDate);
            psm.setString(4, content);
            psm.setInt(5, rs.getId());
            psm.executeUpdate();
            log.info("Lưu thành công!");
        } catch (SQLException ex) {
            log.severe(ex.getMessage());
        }
    }
}
