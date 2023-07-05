package ru.bahmutov.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PersonDto {
    private Long id;
    private String fullName;
}
