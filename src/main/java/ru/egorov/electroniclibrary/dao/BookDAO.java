package ru.egorov.electroniclibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.egorov.electroniclibrary.dao.mapper.BookMapper;
import ru.egorov.electroniclibrary.models.Book;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;
    private final BookMapper bookMapper;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate, BookMapper bookMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookMapper = bookMapper;
    }

    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT book_id, isbn, title, author_id, year_publication, amount, price_per_day, (" +
            "amount - COUNT(date_take_beg) + COUNT(date_take_end) " +
            ") AS remainder " +
            "FROM book LEFT JOIN rent USING(book_id) " +
            "GROUP BY book_id, amount " +
            "ORDER BY title",
            bookMapper);
    }

    public Book get(int id) {
        return jdbcTemplate.query("SELECT book_id, isbn, title, author_id, year_publication, amount, price_per_day, (" +
                "amount - COUNT(date_take_beg) + COUNT(date_take_end) " +
                ") AS remainder " +
                "FROM book LEFT JOIN rent USING(book_id) " +
                "WHERE book_id=? " +
                "GROUP BY book_id, amount " +
                "ORDER BY title", bookMapper, id).stream().findAny().orElse(null);
    }

    public void add(Book book){
        jdbcTemplate.update("INSERT INTO book (isbn, title, author_id, year_publication, amount, price_per_day) VALUES (?,?,?,?,?,?)",
                book.getIsbn(), book.getTitle(), book.getAuthor().getId(), book.getYearPublication(), book.getAmount(), book.getPricePerDay());
    }

    public void update(Book book){
        jdbcTemplate.update("UPDATE book SET isbn=?, title=?, author_id=?, year_publication=?, amount=?, price_per_day=? WHERE book_id=?",
                book.getIsbn(), book.getTitle(), book.getAuthor().getId(), book.getYearPublication(), book.getAmount(), book.getPricePerDay(), book.getId());
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }

    // Check

    public Optional<Book> getBookByISBN(String isbn){
        return jdbcTemplate.query("SELECT book_id, isbn, title, author_id, year_publication, amount, price_per_day, (" +
                        "amount - COUNT(date_take_beg) + COUNT(date_take_end) " +
                        ") AS remainder " +
                        "FROM book LEFT JOIN rent USING(book_id) " +
                        "WHERE isbn=? " +
                        "GROUP BY book_id, amount ",
                bookMapper, isbn).stream().findAny();
    }

}
