package ru.bahmutov.service;

import ru.bahmutov.dao.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers() throws SQLException;
}

