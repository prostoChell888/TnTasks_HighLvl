package ru.bahmutov.repository;

import ru.bahmutov.dao.UserDTO;

import java.util.List;

public interface UserRepository {
    List<UserDTO> getAllUsers();
}
