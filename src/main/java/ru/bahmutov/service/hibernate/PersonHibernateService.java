package ru.bahmutov.service.hibernate;

import lombok.RequiredArgsConstructor;
import ru.bahmutov.models.Person;
import ru.bahmutov.service.PersonService;

import java.util.List;

@RequiredArgsConstructor
public class PersonHibernateService implements PersonService {
    private final PersonHibernateService repository;

    @Override
    public List<Person> getAllUsers() {
        return repository.getAllUsers();
    }
}
