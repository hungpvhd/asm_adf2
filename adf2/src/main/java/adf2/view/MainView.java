package adf2.view;

import adf2.controller.BookController;
import adf2.entity.Book;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainView {
    private final Logger log = Logger.getLogger(MainView.class.getName());
    private BookController bookController = new BookController();
    private Scanner scanner = new Scanner(System.in);

    public void generateMenu() {
        while (true) {
            log.info("------------Book Management-----------");
            log.info("1. Danh sách book.");
            log.info("2. Thêm mới book.");
            log.info("3. Tìm sách theo tiêu đề.");
            log.info("4. Tìm sách theo mã.");
            log.info("5. Cập nhật sách.");
            log.info("6. Xoá sách.");
            log.info("7. Thoát chương trình.");
            log.info("-------------------------------------");
            log.info("Vui lòng nhập lựa chọn của bạn (1 đến 3) : ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    ArrayList<Book> list = bookController.getList();
                    printList(list);
                    break;
                case 2: 
                    bookController.createBook();
                    break;
                case 3:
                    Book account = bookController.getBookByTitle();
                    printDetail(account);
                    break;
                case 4:
                    Book book = bookController.getBookById();
                    printDetail(book);
                    break;
                case 5:
                    bookController.updateBook();
                    break;
                case 6: 
                    bookController.deleteBook();
                    break;
                case 7:
                    log.info("Thoát chương trình. Hẹn lại bạn sau!");
                    break;
                default:
                    log.info("Vui lòng lựa chọn trong khoảng từ 1 đến 3.");
                    break;
            }
            if (choice == 7) {
                break;
            }
        }
    }

    private void printDetail(Book book) {
        if (book == null) {
            log.info("Không tìm thấy book!");
        } else {
            log.info(String.format("Thông tin sách, title: %s, author: %s, releaseDate: %s, content: %s",
                    book.getTitle(),
                    book.getAuthor(),
                    book.getReleaseDate(),
                    book.getContent()
            ));
        }
    }

    private void printList(ArrayList<Book> list) {
        if (list.size() == 0) {
            log.info("Hiện tại không có book trong danh sách.");
            return;
        }
        log.info("------------------------------------------------------------Danh sách các book----------------------------------------------------");
        System.out.printf("%5s %20s %5s %s %5s %20s %5s %s %5s %20s %5s %s %5s %20s %5s\n"
                , "", "Username", "", "|"
                , "", "Password", "", "|"
                , "", "Full Name", "", "|"
                , "", "Created At", "");
        log.info("-------------------------------------------------------------------------------------------------------------------------------------");
        for (Book book : list) {
            System.out.printf("%5s %20s %5s %s %5s %20s %5s %s %5s %20s %5s %s %5s %20s %5s\n"
                    , "", book.getTitle(), "", "|"
                    , "", "*****", "", "|"
                    , "", book.getAuthor(), "", "|"
                    , "", book.getReleaseDate(), "", "|"
                    , "", book.getContent(), "");
        }
    }

}
