package ru.bahmutov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bahmutov.entity.Note;

import java.util.Optional;

@Repository
public interface NoteRepository
        extends JpaRepository<Note, Long> {

    @Override
    Optional<Note> findById(Long id);


}
