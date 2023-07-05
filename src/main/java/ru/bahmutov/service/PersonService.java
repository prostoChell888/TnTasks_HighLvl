package ru.bahmutov.service;

import ru.bahmutov.dto.PersonDto;

import java.util.List;

public interface PersonService {

    List<PersonDto> getAllPeople();

}
