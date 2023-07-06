package ru.bahmutov.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.bahmutov.models.Person;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@Testcontainers
@SpringBootTest
class PersonRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private PersonRepository repository;

    @Test
    @DisplayName("Should return all people")
    void shouldReturnAllPeople() {
        repository.save(new Person(null, "Jon"));
        repository.save(new Person(null, "Jin"));
        repository.save(new Person(null, "Jill"));


        var userList = repository.findAll();
        assertIterableEquals(List.of(
                        new Person(1L, "Jon"),
                        new Person(2L, "Jin"),
                        new Person(3L, "Jill")),
                userList);
    }

  
}