package ru.bahmutov.servises;

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


@Service
@RequiredArgsConstructor
@Validated
public class NoteService {

    private final NoteRepository repository;


    public NoteResponse get( @Min(value = 1) @NotNull Long noteId) {
        var note = repository.findById(noteId).orElseThrow(() -> new NotFoundException("Заметка не найдена"));

        return new NoteResponse(note.getId(), note.getBody(), note.getAuthor());
    }

    public NoteResponse post(@Valid NoteRequest noteRequest) {
        var note = Note.builder().author(noteRequest.author()).body(noteRequest.body()).build();
        note = repository.save(note);
        return new NoteResponse(note.getId(), note.getBody(), note.getAuthor());
    }

    public NoteResponse put( @Min(value = 1) @NotNull Long noteId,
                             @Valid  NoteRequest noteRequest) {
        var note = repository.findById(noteId).orElseThrow(() -> new RuntimeException("Заметка не найдена"));
        note.setBody(noteRequest.body());
        note.setAuthor(noteRequest.author());
        note = repository.save(note);
        return new NoteResponse(note.getId(), note.getBody(), note.getAuthor());
    }

    public void delete(@Min(value = 1) @NotNull Long noteId) {
        var note = repository.findById(noteId).orElseThrow(() -> new RuntimeException("Заметка не найдена"));
        repository.delete(note);
    }



}
