package ru.bahmutov.service.hibernate;

import lombok.RequiredArgsConstructor;
import ru.bahmutov.dto.PersonDto;
import ru.bahmutov.service.PersonService;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
public class PersonHibernateService implements PersonService {
    private final PersonHibernateService repository;

    @Override
    public List<PersonDto> getAllUsers() {
        List<PersonDto> peopleDtoList = new LinkedList<>();
        var banks =  repository.getAllUsers();
        banks.forEach(bank -> peopleDtoList.add(new PersonDto(bank.getId(), bank.getFullName())));
        return peopleDtoList;
    }
}
