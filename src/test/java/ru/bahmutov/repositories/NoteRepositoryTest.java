package ru.bahmutov.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.bahmutov.entity.Note;

import static org.junit.jupiter.api.Assertions.*;


@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class NoteRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    private NoteRepository noteRepository;

    @Test
    @DisplayName("should get note by id ")
    void shouldGetNoteById() {
         noteRepository.save(new Note(null, "qq1", "qq2"));

        var resNote = noteRepository.findById(1L).get();

        assertEquals(1L, resNote.getId());
        assertEquals("qq1", resNote.getBody());
        assertEquals("qq2", resNote.getAuthor());


    }



}