package ru.bahmutov.repository.jdbc;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.bahmutov.configuration.ConfigurationDB;
import ru.bahmutov.dao.BankDTO;
import ru.bahmutov.repository.BankRepository;
import ru.bahmutov.repository.impl.BankJDBCRepository;

import java.sql.SQLException;
import java.util.List;

public class BankJDBCRepositoryTest {

    private static PostgreSQLContainer<?> container;
    private static BankRepository repository;

    @BeforeAll
    public static void setUp() {
        setUpContainer();
        createRepository();
    }

    @BeforeEach
    public void cleanTables() throws SQLException {
        repository.deleteAll();
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
    @DisplayName("Изменине все имен банков в БД")
    void shouldChangeAllNamesOfBunks() throws SQLException {
        var res = repository.save(new BankDTO( "ВТБ"));
        repository.save(new BankDTO( "Сбербанк"));
        repository.save(new BankDTO( "АльфаБанк"));

        List<BankDTO> expectedListOfBunks = List.of(
                  new BankDTO(1L, "New bank name")
                , new BankDTO(2L, "New bank name")
                , new BankDTO(3L, "New bank name"));

        var listOfBunks = repository.updateBankNames("New bank name");

        Assertions.assertIterableEquals(expectedListOfBunks, listOfBunks);
    }

    @Test
    @DisplayName("Сохранение банка в БД")
    void shouldSaveBank() throws SQLException {
        var saveBank = repository.save(new BankDTO("BankName"));
        var bankInBd = repository.getById(saveBank.getId());

        Assertions.assertEquals(bankInBd, saveBank);
    }

    @Test
    @DisplayName("Должее вернуть ввсе сущности банков из БД")
    void shouldReturnAllBanksFRomBd() throws SQLException {
        repository.save(new BankDTO( "ВТБ"));
        repository.save(new BankDTO( "Сбербанк"));
        repository.save(new BankDTO( "АльфаБанк"));

        List<BankDTO> expectedListOfBunks = List.of(
                new BankDTO(1L, "ВТБ")
                , new BankDTO(2L, "Сбербанк")
                , new BankDTO(3L, "АльфаБанк"));

        var listOfBunks = repository.getAllBunks();

        Assertions.assertIterableEquals(expectedListOfBunks, listOfBunks);

    }
}


