package ru.bahmutov.dao;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PersonDTO {
    public PersonDTO(String fullName) {
        this.fullName = fullName;
    }

    private Long id;
    private String fullName;
}
