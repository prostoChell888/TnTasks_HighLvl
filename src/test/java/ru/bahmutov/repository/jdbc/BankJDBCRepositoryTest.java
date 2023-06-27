package ru.bahmutov.repository.jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.bahmutov.configuration.ConfigurationDB;
import ru.bahmutov.repository.BankRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankJDBCRepositoryTest {

    private static PostgreSQLContainer<?> container;
    private static BankRepository repository;

    @BeforeAll
    public static void setUp() {
        setUpContainer();
        createRepository();
    }

    public static void setUpContainer() {
        container = new PostgreSQLContainer<>("postgres:15").withInitScript("init.sql")
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass");
        container.start();
    }

    public static void createRepository() {
        String jdbcUrl = container.getJdbcUrl();
        String username = container.getUsername();
        String password = container.getPassword();

        var config = new ConfigurationDB(jdbcUrl, username, password);
        repository = new BankJDBCRepository(config);
    }

    @AfterAll
    public static void tearDown() {
        container.stop();
    }

    @Test
     void testJdbc() throws SQLException {

        repository.
                updateBankNames("1");

    }
}


