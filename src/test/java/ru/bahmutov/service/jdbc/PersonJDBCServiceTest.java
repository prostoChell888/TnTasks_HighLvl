package ru.bahmutov.service.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bahmutov.dao.PersonDTO;
import ru.bahmutov.repository.impl.PersonJDBCRepository;
import ru.bahmutov.service.impl.PersonJDBCService;

import java.sql.SQLException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PersonJDBCServiceTest {

    @Mock
    private PersonJDBCRepository repository;

    private PersonJDBCService service;


    @BeforeEach
    public  void setUp() {
        service = new PersonJDBCService(repository);
    }

    @Test
    @DisplayName("should get all person")
    void shouldDeleteNote() throws SQLException {
        List<PersonDTO> expectedListOfPerson = List.of(
                new PersonDTO(1L ,"person1"),
                new PersonDTO(2L ,"person2"));

        Mockito.when(repository.getAllPersons()).thenReturn(expectedListOfPerson);

        var people = service.getAllPerson();

        Assertions.assertIterableEquals(expectedListOfPerson, people);
    }
}