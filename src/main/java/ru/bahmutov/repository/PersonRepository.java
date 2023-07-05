package ru.bahmutov.repository;

import ru.bahmutov.models.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> getAllPeople() ;

    Person getById(long id) ;

    Person save(Person person) ;

    void deleteAll() ;
}
