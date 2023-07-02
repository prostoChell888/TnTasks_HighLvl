package ru.bahmutov.service.jdbc;

import lombok.RequiredArgsConstructor;
import ru.bahmutov.dao.PersonDTO;
import ru.bahmutov.repository.jdbc.UserJDBCRepository;
import ru.bahmutov.service.UserService;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class PersonJDBCService implements UserService {

    private final UserJDBCRepository repository;


    @Override
    public List<PersonDTO> getAllUsers() throws SQLException {
        return repository.getAllPersons();
    }
}
