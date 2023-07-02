package ru.bahmutov.service.jdbc;

import lombok.RequiredArgsConstructor;
import ru.bahmutov.dao.PersonDTO;
import ru.bahmutov.repository.jdbc.PersonJDBCRepository;
import ru.bahmutov.service.PersonService;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class PersonJDBCService implements PersonService {

    private final PersonJDBCRepository repository;


    @Override
    public List<PersonDTO> getAllPerson() throws SQLException {
        return repository.getAllPersons();
    }
}
