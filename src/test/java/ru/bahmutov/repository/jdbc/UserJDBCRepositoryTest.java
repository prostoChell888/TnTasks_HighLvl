package ru.bahmutov.repository.jdbc;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.bahmutov.configuration.ConfigurationDB;
import ru.bahmutov.dao.PersonDTO;
import ru.bahmutov.repository.PersonRepository;

import java.sql.SQLException;
import java.util.List;

class UserJDBCRepositoryTest {

    private static PostgreSQLContainer<?> container;
    private static PersonRepository repository;

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
        repository = new PersonJDBCRepository(config);
    }

    @Test
    @DisplayName("Сохранение пользователя в БД")
    void shouldSavePerson() throws SQLException {
        var saveBank = repository.save(new PersonDTO("PersonName"));
        var bankInBd = repository.getById(saveBank.getId());

        Assertions.assertEquals(bankInBd, saveBank);
    }

    @Test
    @DisplayName("Должее вернуть ввсе сущности банков из БД")
    void shouldReturnAllBanksFRomBd() throws SQLException {
        repository.save(new PersonDTO( "Jon"));
        repository.save(new PersonDTO( "Jack"));
        repository.save(new PersonDTO( "Bill"));

        List<PersonDTO> expectedListOfBunks = List.of(
                new PersonDTO(1L, "Jon")
                , new PersonDTO(2L, "Jack")
                , new PersonDTO(3L, "Bill"));

        var listOfBunks = repository.getAllPersons();

        Assertions.assertIterableEquals(expectedListOfBunks, listOfBunks);

    }
}