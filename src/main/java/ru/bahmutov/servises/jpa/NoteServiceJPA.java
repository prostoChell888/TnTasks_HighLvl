package ru.bahmutov.servises.jpa;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import ru.bahmutov.dto.reqest.NoteRequest;
import ru.bahmutov.dto.response.NoteResponse;
import ru.bahmutov.entity.Note;
import ru.bahmutov.exeption.NotFoundException;
import ru.bahmutov.repositories.NoteRepository;
import ru.bahmutov.servises.NoteService;


@Service
@RequiredArgsConstructor
@Validated
public class NoteServiceJPA implements NoteService {

    private static final String NOTE_FOUND_EXCEPTION = "Заметка не найдена";
    private final NoteRepository repository;


    public NoteResponse getNote(@Min(value = 1) @NotNull Long noteId) {
        var note = repository.findById(noteId).orElseThrow(() -> new NotFoundException(NOTE_FOUND_EXCEPTION));

        return new NoteResponse(note.getId(), note.getBody(), note.getAuthor());
    }

    public NoteResponse createNote(@Valid NoteRequest noteRequest) {
        var note = Note.builder().author(noteRequest.author()).body(noteRequest.body()).build();
        note = repository.save(note);
        return new NoteResponse(note.getId(), note.getBody(), note.getAuthor());
    }

    public NoteResponse changeNote(@Min(value = 1) @NotNull Long noteId,
                                   @Valid NoteRequest noteRequest) {
        var note = repository.findById(noteId).orElseThrow(() -> new NotFoundException(NOTE_FOUND_EXCEPTION));
        note.setBody(noteRequest.body());
        note.setAuthor(noteRequest.author());
        note = repository.save(note);
        return new NoteResponse(note.getId(), note.getBody(), note.getAuthor());
    }

    public void deleteNote(@Min(value = 1) @NotNull Long noteId) {
        var note = repository.findById(noteId).orElseThrow(() -> new NotFoundException(NOTE_FOUND_EXCEPTION));
        repository.delete(note);
    }
}
