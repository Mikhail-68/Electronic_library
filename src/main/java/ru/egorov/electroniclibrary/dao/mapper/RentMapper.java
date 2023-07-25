package ru.egorov.electroniclibrary.dao.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.egorov.electroniclibrary.dao.BookDAO;
import ru.egorov.electroniclibrary.dao.ClientDAO;
import ru.egorov.electroniclibrary.models.Rent;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Consumer;

@Component
public class RentMapper implements RowMapper<Rent> {

    private final ClientDAO clientDAO;
    private final BookDAO bookDAO;

    @Autowired
    public RentMapper(ClientDAO clientDAO, BookDAO bookDAO) {
        this.clientDAO = clientDAO;
        this.bookDAO = bookDAO;
    }

    @Override
    public Rent mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rent rent = new Rent();
        rent.setId(rs.getInt("rent_id"));
        rent.setDateTakeBeg(rs.getDate("date_take_beg").toLocalDate());
        if(rs.getDate("date_take_end") != null) rent.setDateTakeEnd(rs.getDate("date_take_end").toLocalDate());
        rent.setPriceTakingBook(rs.getInt("price_taking_book"));
        rent.setRentalCost(rs.getInt("rental_cost"));

        // references
        rent.setClient(clientDAO.get(rs.getInt("client_id")));
        rent.setBook(bookDAO.get(rs.getInt("book_id")));

        return rent;
    }
}
