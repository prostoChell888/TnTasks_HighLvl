package ru.bahmutov.service.jdbc;

import lombok.RequiredArgsConstructor;
import ru.bahmutov.dao.UserDTO;
import ru.bahmutov.repository.jdbc.UserJDBCRepository;
import ru.bahmutov.service.UserService;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class UserJDBCService implements UserService {

    private final UserJDBCRepository repository;


    @Override
    public List<UserDTO> getAllUsers() throws SQLException {
        return repository.getAllUsers();
    }
}
