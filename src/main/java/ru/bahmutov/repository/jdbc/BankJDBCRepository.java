package ru.bahmutov.repository.jdbc;

import ru.bahmutov.configuration.ConfigurationDB;
import ru.bahmutov.dao.BankDTO;
import ru.bahmutov.repository.BankRepository;

import java.sql.*;
import java.util.LinkedList;
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
        List<BankDTO> listOfBanks = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(),
                config.getPassword())) {

            try (var queryStatement = connection.createStatement();
                 var resSet = queryStatement.executeQuery(selectQuery)) {
                while (resSet.next()) {
                    listOfBanks.add(new BankDTO(resSet.getLong("id"), resSet.getString("name")));
                }
            }
        }

        return listOfBanks;
    }

    @Override
    public void save(BankDTO bank) throws SQLException {
        String selectQuery = "INSERT INTO bank (name) " +
                "VALUES (?)";
        List<BankDTO> listOfBanks = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(),
                config.getPassword())) {
            try (var statement =connection.prepareStatement(selectQuery);) {
                statement.setString(1, bank.getName());
            }

        }
    }
}
