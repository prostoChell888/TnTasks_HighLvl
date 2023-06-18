package ru.bahmutov.repository;

import ru.bahmutov.models.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
}
