package ru.bahmutov.dto.response;


public record NoteResponse(
        Long id,
        String body,
        String author
) { }
