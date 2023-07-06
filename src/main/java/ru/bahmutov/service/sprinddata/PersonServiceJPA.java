package ru.bahmutov.service.sprinddata;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bahmutov.models.Person;
import ru.bahmutov.repository.PersonRepository;
import ru.bahmutov.service.PersonService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PersonServiceJPA implements PersonService {

    private final PersonRepository repository;

    @Override
    public List<Person> getAllUsers() {
        return repository.findAll();
    }
}
