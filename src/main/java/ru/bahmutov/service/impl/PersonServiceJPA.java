package ru.bahmutov.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bahmutov.dto.PersonDto;
import ru.bahmutov.models.Person;
import ru.bahmutov.repository.PersonRepository;
import ru.bahmutov.service.PersonService;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PersonServiceJPA implements PersonService {

    private final PersonRepository repository;

    @Override
    public List<PersonDto> getAllUsers() {
        List<Person> people = repository.findAll();

        List<PersonDto> peopleDto = new ArrayList<>();
        for (var person: people) {
            peopleDto.add(new PersonDto(person.getId(), person.getFullName()));
        }

        return peopleDto;
    }
}
