package ru.bahmutov.service.sprinddata;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bahmutov.models.User;
import ru.bahmutov.repository.UserRepository;
import ru.bahmutov.service.UserService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceJPA implements UserService {

    private final UserRepository repository;

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
