package ru.bahmutov.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.bahmutov.models.Bank;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;


@Testcontainers
@SpringBootTest
class BankRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private BankRepository repository;

    @BeforeEach
    public void setUpSession() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("should update all banks names ")
    void shouldGetNoteById() {
        repository.save(new Bank(null, "ВТБ"));
        repository.save(new Bank(null, "Сбербанк"));
        repository.save(new Bank(null, "АльфаБанк"));

        List<Bank> expectedListOfBunks = List.of(
                new Bank(1L, "New bank name")
                , new Bank(2L, "New bank name")
                , new Bank(3L, "New bank name"));

        repository.updateBankNames("new bank names");
        var listOfBunks = repository.findAll();

        assertIterableEquals(expectedListOfBunks, listOfBunks);
    }

}