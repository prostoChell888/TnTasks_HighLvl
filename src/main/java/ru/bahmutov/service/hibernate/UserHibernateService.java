package ru.bahmutov.service.hibernate;

import lombok.RequiredArgsConstructor;
import ru.bahmutov.models.User;
import ru.bahmutov.service.UserService;

import java.util.List;

@RequiredArgsConstructor
public class UserHibernateService implements UserService {
    private final UserHibernateService repository;

    @Override
    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }
}
