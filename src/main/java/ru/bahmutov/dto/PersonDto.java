package ru.bahmutov.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class PersonDto {
    private Long id;
    private String fullName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonDto personDto = (PersonDto) o;

        if (!Objects.equals(id, personDto.id)) return false;
        return Objects.equals(fullName, personDto.fullName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        return result;
    }
}
