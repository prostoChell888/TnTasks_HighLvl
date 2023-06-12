package ru.bahmutov.dto.reqest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NoteRequest(
        @NotBlank(message = "Заметка не должна быть пустой")
        @NotNull
        String body,

        @Size(min = 2, message = "Имя автора слишко короткое должно быть больше 2 симоволов")
        @Size(max = 200, message = "Имя автора слишко длинное должно быть меньше 200 симоволов")
        String author) { }
