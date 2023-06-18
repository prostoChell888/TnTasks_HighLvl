package ru.bahmutov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bahmutov.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
