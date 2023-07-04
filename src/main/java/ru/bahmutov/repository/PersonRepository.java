package ru.bahmutov.repository;

import ru.bahmutov.models.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> getAllUsers() ;

    Person getById(long id) ;

    Person save(Person person) ;

    void deleteAll() ;
}
