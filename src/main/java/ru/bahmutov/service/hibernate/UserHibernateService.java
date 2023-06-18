package ru.bahmutov.service.hibernate;

import lombok.RequiredArgsConstructor;
import ru.bahmutov.dao.UserDTO;
import ru.bahmutov.repository.hibernate.BankHibernateRepository;
import ru.bahmutov.service.UserService;

import java.util.List;

@RequiredArgsConstructor
public class UserHibernateService implements UserService {
    private final UserHibernateService repository;

    @Override
    public List<UserDTO> getAllUsers() {
        return repository.getAllUsers();
    }
}
