package adf2.entity;

public class Book {
    
    private int id;
    private String title;
    private String author;
    private String releaseDate;
    private String content;

    public Book() {
    }

    public Book(String title, String author, String releaseDate, String content) {
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
