package ru.bahmutov.servises;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import ru.bahmutov.dto.reqest.NoteRequest;
import ru.bahmutov.dto.response.NoteResponse;

public interface NoteService {

    NoteResponse get(@Min(value = 1) @NotNull Long noteId);

    NoteResponse post(@Valid NoteRequest noteRequest);

    NoteResponse put(@Min(value = 1) @NotNull Long noteId,
                     @Valid  NoteRequest noteRequest);

    void delete(@Min(value = 1) @NotNull Long noteId);
}
