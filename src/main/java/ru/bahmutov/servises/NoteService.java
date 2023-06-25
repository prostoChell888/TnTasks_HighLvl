package ru.bahmutov.servises;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import ru.bahmutov.dto.reqest.NoteRequest;
import ru.bahmutov.dto.response.NoteResponse;
import ru.bahmutov.entity.Note;
import ru.bahmutov.exeption.NotFoundException;

public interface NoteService {

    NoteResponse get(Long noteId);

    NoteResponse post(NoteRequest noteRequest);

    NoteResponse put(Long noteId, NoteRequest noteRequest);

    void delete(Long noteId);

}
