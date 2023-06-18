package ru.bahmutov;

import ru.bahmutov.configuration.ConfigurationDB;
import ru.bahmutov.repository.UserRepository;
import ru.bahmutov.repository.jdbc.UserJDBCRepository;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        var prop = new ConfigurationDB("application.properties");
        UserRepository repository = new UserJDBCRepository(prop);
        var res = repository.getAllUsers();
        res.forEach(System.out::println);
    }
}