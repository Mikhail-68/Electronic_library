package ru.egorov.electroniclibrary.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.egorov.electroniclibrary.dao.mapper.ClientMapper;
import ru.egorov.electroniclibrary.models.Client;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientDAO {

    private final JdbcTemplate jdbcTemplate;

    public ClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Client> getAll() {
        return jdbcTemplate.query("SELECT * FROM client ORDER BY surname, name", new ClientMapper());
    }

    public Client get(int id) {
        return jdbcTemplate.query("SELECT * FROM client WHERE client_id=?", new ClientMapper(), id)
                .stream().findAny().orElse(null);
    }

    public void add(Client client) {
        jdbcTemplate.update("INSERT INTO client (name, surname, date_of_birth, phone) VALUES (?, ?, ?, ?)",
                client.getName(), client.getSurname(), client.getDateOfBirth(), client.getPhone());
    }

    public void update(Client client) {
        jdbcTemplate.update("UPDATE client SET name=?, surname=?, date_of_birth=?, phone=? WHERE client_id=?",
                client.getName(), client.getSurname(), client.getDateOfBirth(), client.getPhone(), client.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM client WHERE client_id=?", id);
    }

    // Validation

    public Optional<Client> checkingForFullness(Client client){
        return jdbcTemplate.query("SELECT * FROM client WHERE name=? and surname=? and date_of_birth=? and client_id <> ?;",
                new ClientMapper(), client.getName(), client.getSurname(), client.getDateOfBirth(), client.getId()).stream().findAny();
    }

}
