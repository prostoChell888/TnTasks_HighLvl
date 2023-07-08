package ru.bahmutov.service.hibernate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bahmutov.dto.PersonDto;
import ru.bahmutov.models.Person;
import ru.bahmutov.repository.hibernate.PersonHibernateRepository;
import ru.bahmutov.service.impl.PersonHibernateService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;


@ExtendWith(MockitoExtension.class)
class PersonHibernateServiceTest {
    @Mock
    private PersonHibernateRepository repository;

    private PersonHibernateService service;


    @BeforeEach
    public  void setUp() {
        service = new PersonHibernateService(repository);
    }

    @Test
    @DisplayName("should get all person")
    void shouldGetAllPeople()  {
        List<PersonDto> expectedListOfPerson = List.of(
                new PersonDto(1L ,"person1"),
                new PersonDto(2L ,"person2"));

        Mockito.when(repository.getAllPeople()).thenReturn(List.of(
                new Person(1L ,"person1"),
                new Person(2L ,"person2")));

        var people = service.getAllPeople();

        assertIterableEquals(expectedListOfPerson, people);
    }
}