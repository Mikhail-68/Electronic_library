package ru.egorov.electroniclibrary.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Book {

    private int id;
    @Pattern(regexp = "\\d{13}", message = "ISBN должен содержать 13 цифр")
    private String isbn;
    @NotBlank(message = "Название книги не должно быть пустым")
    @Size(min = 2, max = 50, message = "Длина названия должна быть в диапазоне от 2 до 50 символов")
    private String title;
    @Min(value = 0, message = "Дата публикации должна быть больше 0")
    private int yearPublication;
    @Min(value = 0, message = "Количество книг должно быть больше 0")
    private int amount;
    @Min(value = 0, message = "Цена книги должна быть больше 0")
    private int pricePerDay;
    private int remainder;

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

    public int getRemainder() {
        return remainder;
    }

    public void setRemainder(int remainder) {
        this.remainder = remainder;
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
