package ru.egorov.electroniclibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.egorov.electroniclibrary.dao.mapper.RentMapper;
import ru.egorov.electroniclibrary.models.Rent;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RentDAO {
    private final JdbcTemplate jdbcTemplate;
    private final RentMapper rentMapper;

    @Autowired
    public RentDAO(JdbcTemplate jdbcTemplate, RentMapper rentMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rentMapper = rentMapper;
    }

    public List<Rent> getAll() {
        return jdbcTemplate.query("SELECT rent_id, client_id, book_id, date_take_beg, date_take_end, price_taking_book, " +
                "EXTRACT(DAY FROM date_take_end::timestamp - date_take_beg::timestamp) * price_taking_book " +
                "as rental_cost " +
                "FROM rent " +
                "ORDER BY date_take_end DESC, date_take_beg DESC", rentMapper);
    }

    public List<Rent> getByBook(int bookId) {
        return jdbcTemplate.query("SELECT rent_id, client_id, book_id, date_take_beg, date_take_end, price_taking_book, " +
                        "EXTRACT(DAY FROM date_take_end::timestamp - date_take_beg::timestamp) * price_taking_book " +
                        "as rental_cost " +
                        "FROM rent " +
                        "WHERE book_id=? " +
                        "ORDER BY date_take_end DESC, date_take_beg DESC",
                rentMapper, bookId);
    }

    public List<Rent> getByClient(int clientId) {
        return jdbcTemplate.query("SELECT rent_id, client_id, book_id, date_take_beg, date_take_end, price_taking_book, " +
                        "EXTRACT(DAY FROM date_take_end::timestamp - date_take_beg::timestamp) * price_taking_book " +
                        "as rental_cost " +
                        "FROM rent " +
                        "WHERE client_id=? " +
                        "ORDER BY date_take_end DESC, date_take_beg DESC",
                rentMapper, clientId);
    }

    public void add(int clientId, int bookId) {
        jdbcTemplate.update("INSERT INTO rent (client_id, book_id, date_take_beg, price_taking_book) " +
                        "VALUES (?, ?, ?, (SELECT price_per_day FROM book WHERE book_id=?))",
                clientId, bookId, LocalDate.now(), bookId);
    }

    public void confirmReturnBook(int id) {
        jdbcTemplate.update("UPDATE rent SET date_take_end=? " +
                "WHERE rent_id=?", LocalDate.now(), id);
    }

}
