package ru.bahmutov.repository;

import ru.bahmutov.dao.PersonDTO;

import java.sql.SQLException;
import java.util.List;

public interface PersonRepository {
    List<PersonDTO> getAllPersons() throws SQLException;

    PersonDTO getById(long id) throws SQLException;

    PersonDTO save(PersonDTO person) throws SQLException;

    void deleteAll() throws SQLException;
}
