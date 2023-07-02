package ru.bahmutov.service;

import ru.bahmutov.dao.PersonDTO;

import java.sql.SQLException;
import java.util.List;

public interface PersonService {

    List<PersonDTO> getAllPerson() throws SQLException;
}

