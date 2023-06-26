package ru.bahmutov.servises.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bahmutov.dto.reqest.NoteRequest;
import ru.bahmutov.dto.response.NoteResponse;
import ru.bahmutov.entity.Note;
import ru.bahmutov.exeption.NotFoundException;
import ru.bahmutov.repositories.NoteRepository;
import ru.bahmutov.servises.NoteService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class NoteServiceJPATest {
    @Mock
    private NoteRepository noteRepository;

    private NoteService noteService;


    @BeforeEach
    public  void setUp() {
         noteService = new NoteServiceJPA(noteRepository);
    }

    @Test
    @DisplayName("should find note by id")
    void shouldFindNoteById() {
        var note = new Note(1L, "body", "Author");
        var expectedNoteResponse = new NoteResponse(1L, "body", "Author");
        Mockito.when(noteRepository.findById(1L)).thenReturn(Optional.of(note));

        NoteResponse noteResponse = noteService.getNote(1L);

        assertThat(noteResponse).isEqualTo(expectedNoteResponse);
    }

    @Test
    @DisplayName("should not find note by id")
    void shouldNotFindNoteById() {
        Mockito.when(noteRepository.findById(1L)).thenThrow(new NotFoundException("Заметка не найдена"));

        assertThatThrownBy(() -> noteService.getNote(1L)).isInstanceOf(NotFoundException.class)
                .hasMessage("Заметка не найдена");
    }

    //    @Test
//    @DisplayName("Throw Bad Request when pass negative id")
//    void shouldThrowBadRequestWhenGetById() {
//        NoteService noteService = new NoteServiceJPA(null);
//
//        assertThatThrownBy(() -> noteService.get(-1L)).isInstanceOf(MethodArgumentNotValidException.class)
//                .hasMessage("get.noteId: должно быть не меньше 1");
//    }

    @Test
    @DisplayName("should add new note")
    void shouldAddNewNote() {
        var noteRequest = new NoteRequest("body", "author");
        var noteModelResponse = Note.builder().id(1L).author(noteRequest.author()).body(noteRequest.body()).build();

        Mockito.when(noteRepository.save(any(Note.class))).thenReturn(noteModelResponse);

        var noteResponse = noteService.createNote(noteRequest);
        assertThat(noteResponse).isEqualTo(new NoteResponse(1L, "body", "author"));
    }


//    @Test
//    void shouldThrowNotValidNoteValueWhenAddToNewNote() {
//        NoteService noteService = new NoteServiceJPA(null);
//        var noteRequest = new NoteRequest("body", "");
//
//        assertThatThrownBy(() -> noteService.post(noteRequest)).isInstanceOf(ConstraintViolationException.class);
//    }


    @DisplayName("should change note")
    @Test
    void shouldChangeNote() {
        Mockito.when(noteRepository.findById(1L)).thenReturn(Optional.of(new Note(1L , "body", "author")));
        Mockito.when(noteRepository.save(any(Note.class))).thenReturn(new Note(1L , "body", "authorChanged"));

        var noteRequest = new NoteRequest("body", "authorChanged");
        var changedNote = noteService.changeNote(1L, noteRequest);

        assertThat(changedNote).isEqualTo(new NoteResponse(1L, "body", "authorChanged"));
    }

    @DisplayName("should change note")
    @Test
    void shouldDeleteNote() {
        Mockito.when(noteRepository.findById(1L)).thenReturn(Optional.of(new Note(1L , "body", "author")));

        noteService.deleteNote(1L);

        Mockito.verify(noteRepository, Mockito.times(1)).delete(any(Note.class));
    }


}