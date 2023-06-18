package ru.bahmutov.repository.jdbc;

import ru.bahmutov.configuration.ConfigurationDB;
import ru.bahmutov.dao.UserDTO;
import ru.bahmutov.repository.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserJDBCRepository implements UserRepository {

    private final ConfigurationDB config;

    public UserJDBCRepository(ConfigurationDB config)
            {
        if (config == null) {
            throw new IllegalArgumentException("Configuration file cannot be null");
        }
        this.config = config;
    }

    @Override
    public List<UserDTO> getAllUsers() throws SQLException {
        String selectQuery = "SELECT * FROM person";
        List<UserDTO> listOfPeople = new LinkedList<>();
        try (Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(),
                config.getPassword())) {

            try (var queryStatement = connection.createStatement();
                 var resSet = queryStatement.executeQuery(selectQuery)) {
                while (resSet.next()) {
                    listOfPeople.add(new UserDTO(resSet.getLong("id"), resSet.getString("full_name")));
                }
            }
        }

        return listOfPeople;
    }
}
