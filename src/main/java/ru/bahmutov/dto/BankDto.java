package ru.bahmutov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;


@Getter
@AllArgsConstructor
@ToString
public class BankDto {

    private Long id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankDto bankDto = (BankDto) o;

        if (!Objects.equals(id, bankDto.id)) return false;
        return Objects.equals(name, bankDto.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
