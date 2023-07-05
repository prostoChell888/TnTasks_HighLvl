package ru.bahmutov.repository.hibernate;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.bahmutov.models.Person;
import ru.bahmutov.repository.PersonRepository;
import ru.bahmutov.util.HibernateUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class PersonHibernateRepositoryTest {
    private static PostgreSQLContainer<?> container;
    private static PersonRepository repository;


    @BeforeAll
    public static void setUp() {
        setUpContainer();
        createRepository();
    }

    @BeforeEach
    public void setUpSession() {
       repository.deleteAll();
    }

    public static void setUpContainer() {
        container = new PostgreSQLContainer<>("postgres:15")
                .withInitScript("init.sql")
                .withDatabaseName("UserAndBanks")
                .withUsername("postgres")
                .withPassword("123");
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
        repository.save(new Person(null, "Jon"));
        repository.save(new Person(null, "Jin"));
        repository.save(new Person(null, "Jill"));


        var userList = repository.getAllPeople();
        assertIterableEquals(List.of(
                new Person(1L, "Jon"),
                new Person(2L, "Jin"),
                new Person(3L, "Jill")),
                userList);
    }

    @Test
    @DisplayName("Сохранение пользователя в БД")
    void shouldSavePerson()  {
        var savePerson = repository.save(new Person(null, "PersonName"));
        var personFromBd = repository.getById(savePerson.getId());

        Assertions.assertEquals(personFromBd, savePerson);
    }
}