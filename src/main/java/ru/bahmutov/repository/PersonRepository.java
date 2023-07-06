package ru.bahmutov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bahmutov.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
