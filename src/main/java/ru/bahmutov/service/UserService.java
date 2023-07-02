package ru.bahmutov.service;

import ru.bahmutov.dao.PersonDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<PersonDTO> getAllUsers() throws SQLException;
}

