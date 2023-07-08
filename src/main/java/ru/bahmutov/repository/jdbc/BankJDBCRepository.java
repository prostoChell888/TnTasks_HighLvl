package ru.bahmutov.repository.jdbc;

import ru.bahmutov.configuration.ConfigurationDB;
import ru.bahmutov.dao.BankDTO;
import ru.bahmutov.repository.BankRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BankJDBCRepository implements BankRepository {

    private final ConfigurationDB config;


    public BankJDBCRepository(ConfigurationDB config) {
        if (config == null) {
            throw new IllegalArgumentException("Configuration file cannot be null");
        }
        this.config = config;
    }


    @Override
    public List<BankDTO> updateBankNames(String newName) throws SQLException {
        if (newName == null) return Collections.emptyList();
        String updateQuery = "UPDATE bank SET name = ?";
        try (Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(),
                config.getPassword())) {

            connection.setAutoCommit(false);
            try (var statement = connection.prepareStatement(updateQuery)) {
                statement.setString(1, newName);
                statement.executeUpdate();
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
        }

        return getAllBunks();
    }


    @Override
    public List<BankDTO> getAllBunks() throws SQLException {
        String selectQuery = "SELECT * FROM bank";
        List<BankDTO> listOfBanks = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(),
                config.getPassword())) {
            connection.setAutoCommit(false);
            try (var queryStatement = connection.createStatement();
                 var resSet = queryStatement.executeQuery(selectQuery)) {
                while (resSet.next()) {
                    listOfBanks.add(new BankDTO(resSet.getLong("id"), resSet.getString("name")));
                }
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
        }

        return listOfBanks;
    }


    @Override
    public BankDTO getById(long id) throws SQLException {
        String query = "SELECT * FROM bank WHERE id = ?";
        BankDTO result = null;
        try (Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword())) {
            connection.setAutoCommit(false);
            try (var statement = connection.prepareStatement(query)) {
                statement.setLong(1, id);
                var resSet = statement.executeQuery();
                resSet.next();
                result = new BankDTO(resSet.getLong("id"), resSet.getString("name"));
            } catch (Exception e) {
                connection.rollback();
            } finally {
                connection.setAutoCommit(true);
            }
        }

        return result;
    }


    @Override
    public BankDTO save(BankDTO bank) throws SQLException {
        if (bank == null) return null;

        String query = "INSERT INTO bank (name) VALUES (?)";
        Long generatedId = null;
        try (Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(),
                config.getPassword())) {
            connection.setAutoCommit(false);
            try (var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, bank.getName());
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
        String deleteQuery = "TRUNCATE person_bank, bank RESTART IDENTITY";

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
