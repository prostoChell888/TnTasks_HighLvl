package ru.bahmutov.repository.jdbc;

import ru.bahmutov.configuration.ConfigurationDB;
import ru.bahmutov.dao.PersonDTO;
import ru.bahmutov.repository.PersonRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonJDBCRepository implements PersonRepository {

    private final ConfigurationDB config;

    public PersonJDBCRepository(ConfigurationDB config)
            {
        if (config == null) {
            throw new IllegalArgumentException("Configuration file cannot be null");
        }
        this.config = config;
    }

    @Override
    public List<PersonDTO> getAllPersons() throws SQLException {
        String selectQuery = "SELECT * FROM person";
        List<PersonDTO> listOfPeople = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(),
                config.getPassword())) {

            try (var queryStatement = connection.createStatement();
                 var resSet = queryStatement.executeQuery(selectQuery)) {
                while (resSet.next()) {
                    listOfPeople.add(new PersonDTO(resSet.getLong("id"), resSet.getString("full_name")));
                }
            }
        }

        return listOfPeople;
    }

    @Override
    public PersonDTO getById(long id) throws SQLException {
        String query = "SELECT * FROM person WHERE id = ?";
        PersonDTO result = null;
        try (Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword())) {
            connection.setAutoCommit(false);
            try (var statement = connection.prepareStatement(query)) {
                statement.setLong(1, id);
                var resSet = statement.executeQuery();
                resSet.next();
                result = new PersonDTO(resSet.getLong("id"), resSet.getString("full_name"));
            } catch (Exception e) {
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
        }

        return result;
    }

    @Override
    public PersonDTO save(PersonDTO person) throws SQLException {
        if (person == null) return null;

        String query = "INSERT INTO person (full_name) VALUES (?)";
        Long generatedId = null;
        try (Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(),
                config.getPassword())) {
            connection.setAutoCommit(false);
            try (var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, person.getFullName());
                statement.executeUpdate();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next())
                        generatedId = generatedKeys.getLong(1);
                }
            } catch (Exception e) {
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
        }

        return (generatedId == null) ? null : getById(generatedId);
    }

    @Override
    public void deleteAll() throws SQLException {
        String deleteQuery = "TRUNCATE person_bank, person RESTART IDENTITY";

        try (var connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword())) {
            connection.setAutoCommit(false);
            try (var queryStatement = connection.createStatement()) {
                queryStatement.executeUpdate(deleteQuery);
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }
}
