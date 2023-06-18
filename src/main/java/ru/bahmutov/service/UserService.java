package ru.bahmutov.service;

import ru.bahmutov.dao.UserDTO;

import java.util.List;

public interface UserService {
    public interface UserRepository {
        List<UserDTO> getAllUsers();
    }
}
