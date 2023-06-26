package ru.bahmutov.servises;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import ru.bahmutov.dto.reqest.NoteRequest;
import ru.bahmutov.dto.response.NoteResponse;

public interface NoteService {

    NoteResponse getNote(@Min(value = 1) @NotNull Long noteId);

    NoteResponse createNote(@Valid NoteRequest noteRequest);

    NoteResponse changeNote(@Min(value = 1) @NotNull Long noteId,
                            @Valid  NoteRequest noteRequest);

    void deleteNote(@Min(value = 1) @NotNull Long noteId);
}
