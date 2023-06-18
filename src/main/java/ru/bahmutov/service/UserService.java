package ru.bahmutov.service;

import ru.bahmutov.models.User;

import java.util.List;

public interface UserService {
    public interface UserRepository {
        List<User> getAllUsers();
    }
}
