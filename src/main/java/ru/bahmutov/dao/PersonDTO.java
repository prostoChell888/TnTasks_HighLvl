package ru.bahmutov.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PersonDTO {
    public PersonDTO(String fullName) {
        this.fullName = fullName;
    }

    private Long id;
    private String fullName;
}
