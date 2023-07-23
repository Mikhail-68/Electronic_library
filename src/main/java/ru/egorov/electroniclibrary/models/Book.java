package ru.egorov.electroniclibrary.models;

public class Book {

    private int id;
    private String isbn;
    private String title;
    private int yearPublication;
    private int amount;
    private int pricePerDay;

    // references author
    private Author author;

    public Book() {

    }

    public Book(int id, String isbn, String title, int yearPublication, int amount, int pricePerDay) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.yearPublication = yearPublication;
        this.amount = amount;
        this.pricePerDay = pricePerDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book[" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", yearPublication=" + yearPublication +
                ", amount=" + amount +
                ']';
    }
}
