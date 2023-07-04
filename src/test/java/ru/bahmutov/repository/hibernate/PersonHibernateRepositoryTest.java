package ru.bahmutov.repository.hibernate;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.bahmutov.repository.PersonRepository;
import ru.bahmutov.util.HibernateUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class PersonHibernateRepositoryTest {
    private static PostgreSQLContainer<?> container;
    private static PersonRepository repository;

    private static SessionFactory sessionFactory;

    @BeforeAll
    public static void setUp() {
        setUpContainer();
        createRepository();
    }

    @BeforeEach
    public void setUpSession() {
        sessionFactory = HibernateUtil.getSessionFactoryWithCustomConfig(container.getJdbcUrl(), container.getUsername(), container.getPassword());
        repository = new PersonHibernateRepository(sessionFactory);
    }

    @AfterEach
    public void closeSession() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
//
//    @BeforeEach
//    public void cleanTables() throws SQLException {
//        repository.deleteAll();
//    }

    public static void setUpContainer() {
        container = new PostgreSQLContainer<>("postgres:15")
                .withInitScript("init.sql")
                .withDatabaseName("recipes")
                .withUsername("postgres")
                .withPassword("postgres");
        container.start();
    }

    public static void createRepository() {
        String jdbcUrl = container.getJdbcUrl();
        String username = container.getUsername();
        String password = container.getPassword();

        var sessionFactory =  HibernateUtil.getSessionFactoryWithCustomConfig(jdbcUrl, username, password);
        repository = new PersonHibernateRepository(sessionFactory);
    }

    @Test
    @DisplayName("Should return all people")
    void shouldReturnAllPeople() {
        var userList = repository.getAllUsers();
        assertIterableEquals(List.of(), userList);
    }

//    @Test
//    @DisplayName("Сохранение пользователя в БД")
//    void shouldSavePerson() throws SQLException {
//        var saveBank = repository.save(new PersonDTO("PersonName"));
//        var bankInBd = repository.getById(saveBank.getId());
//
//        Assertions.assertEquals(bankInBd, saveBank);
//    }
//
//    @Test
//    @DisplayName("Должен вернуть ввсе сущности банков из БД")
//    void shouldReturnAllBanksFRomBd() throws SQLException {
//        repository.save(new PersonDTO( "Jon"));
//        repository.save(new PersonDTO( "Jack"));
//        repository.save(new PersonDTO( "Bill"));
//
//        List<PersonDTO> expectedListOfBunks = List.of(
//                new PersonDTO(1L, "Jon")
//                , new PersonDTO(2L, "Jack")
//                , new PersonDTO(3L, "Bill"));
//
//        var listOfBunks = repository.getAllPersons();
//
//        Assertions.assertIterableEquals(expectedListOfBunks, listOfBunks);
//
//    }


  
}