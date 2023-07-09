package ru.bahmutov.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bahmutov.dto.PersonDto;
import ru.bahmutov.models.Person;
import ru.bahmutov.repository.PersonRepository;
import ru.bahmutov.service.impl.PersonServiceJPA;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;


@ExtendWith(MockitoExtension.class)
class PersonServiceJPATest {

    @Mock
    private PersonRepository personRepository;

    private PersonService personService;

    @BeforeEach
    public void setUp() {
        personService = new PersonServiceJPA(personRepository);
    }


    @Test
    @DisplayName("should find note by id")
    void shouldUpdateAllBanks() {
        Mockito.when(personRepository.findAll()).thenReturn(List.of(
                new Person(1L, "Jon"),
                new Person(2L, "Jim"),
                new Person(3L, "Bil")));



        List<PersonDto> people = personService.getAllUsers();

        assertIterableEquals(List.of(
                new PersonDto(1L, "Jon"),
                new PersonDto(2L, "Jim"),
                new PersonDto(3L, "Bil")), people);
    }
}