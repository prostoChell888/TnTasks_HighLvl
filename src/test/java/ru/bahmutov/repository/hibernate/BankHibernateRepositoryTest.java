package ru.bahmutov.repository.hibernate;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.bahmutov.models.Bank;
import ru.bahmutov.repository.BankRepository;
import ru.bahmutov.repository.impl.BankHibernateRepository;
import ru.bahmutov.util.HibernateUtil;

import java.util.List;

class BankHibernateRepositoryTest {
    private static PostgreSQLContainer<?> container;
    private static BankRepository repository;


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
        repository = new BankHibernateRepository(sessionFactory);
    }

    @Test
    @DisplayName("Изменине все имен банков в БД")
    void shouldChangeAllNamesOfBunks() {
        repository.save(new Bank( "ВТБ"));
        repository.save(new Bank( "Сбербанк"));
        repository.save(new Bank( "АльфаБанк"));

        List<Bank> expectedListOfBunks = List.of(
                new Bank(1L, "New bank name")
                , new Bank(2L, "New bank name")
                , new Bank(3L, "New bank name"));

        var listOfBunks = repository.updateBankNames("New bank name");

        Assertions.assertIterableEquals(expectedListOfBunks, listOfBunks);
    }

    @Test
    @DisplayName("Сохранение банка в БД")
    void shouldSaveBank()  {
        var saveBank = repository.save(new Bank("BankName"));
        var bankInBd = repository.getById(saveBank.getId());

        Assertions.assertEquals(bankInBd, saveBank);
    }

    @Test
    @DisplayName("Должее вернуть ввсе сущности банков из БД")
    void shouldReturnAllBanksFRomBd()  {
        repository.save(new Bank( "ВТБ"));
        repository.save(new Bank( "Сбербанк"));
        repository.save(new Bank( "АльфаБанк"));

        List<Bank> expectedListOfBunks = List.of(
                new Bank(1L, "ВТБ")
                , new Bank(2L, "Сбербанк")
                , new Bank(3L, "АльфаБанк"));

        var listOfBunks = repository.getAllBunks();

        Assertions.assertIterableEquals(expectedListOfBunks, listOfBunks);
    }
}