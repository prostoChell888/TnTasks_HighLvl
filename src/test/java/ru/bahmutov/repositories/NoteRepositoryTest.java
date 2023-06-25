package ru.bahmutov.repositories;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
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
    void shouldSaveNote() {
         noteRepository.save(new Note(null, "qq", "qq"));

        var res = noteRepository.findAll();

        assertEquals(1, res.size());
    }

}