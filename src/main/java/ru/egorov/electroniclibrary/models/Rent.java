package ru.egorov.electroniclibrary.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Optional;

public class Rent {

    private int id;
    private Client client;
    private Book book;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTakeBeg;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTakeEnd;
    private int priceTakingBook;

    private int rentalCost;

    public Rent() {
    }

    public Rent(LocalDate dateTakeBeg, LocalDate dateTakeEnd, int priceTakingBook) {
        this.dateTakeBeg = dateTakeBeg;
        this.dateTakeEnd = dateTakeEnd;
        this.priceTakingBook = priceTakingBook;
    }

    public Rent(int id, Client client, Book book, LocalDate dateTakeBeg, LocalDate dateTakeEnd, int priceTakingBook) {
        this.id = id;
        this.client = client;
        this.book = book;
        this.dateTakeBeg = dateTakeBeg;
        this.dateTakeEnd = dateTakeEnd;
        this.priceTakingBook = priceTakingBook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Optional<Book> getBook() {
        return Optional.ofNullable(book);
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDateTakeBeg() {
        return dateTakeBeg;
    }

    public void setDateTakeBeg(LocalDate dateTakeBeg) {
        this.dateTakeBeg = dateTakeBeg;
    }

    public Optional<LocalDate> getDateTakeEnd() {
        return Optional.ofNullable(dateTakeEnd);
    }

    public void setDateTakeEnd(LocalDate dateTakeEnd) {
        this.dateTakeEnd = dateTakeEnd;
    }

    public int getPriceTakingBook() {
        return priceTakingBook;
    }

    public void setPriceTakingBook(int priceTakingBook) {
        this.priceTakingBook = priceTakingBook;
    }

    public int getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(int rentalCost) {
        this.rentalCost = rentalCost;
    }
}
