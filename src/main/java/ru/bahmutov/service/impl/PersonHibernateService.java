package ru.bahmutov.service.impl;

import lombok.RequiredArgsConstructor;
import ru.bahmutov.dto.PersonDto;
import ru.bahmutov.repository.impl.PersonHibernateRepository;
import ru.bahmutov.service.PersonService;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
public class PersonHibernateService implements PersonService {
    private final PersonHibernateRepository repository;

    @Override
    public List<PersonDto> getAllPeople() {
        List<PersonDto> peopleDtoList = new LinkedList<>();
        var banks =  repository.getAllPeople();
        banks.forEach(bank -> peopleDtoList.add(new PersonDto(bank.getId(), bank.getFullName())));
        return peopleDtoList;
    }
}
