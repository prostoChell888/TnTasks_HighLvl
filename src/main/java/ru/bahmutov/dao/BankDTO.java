package ru.bahmutov.dao;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class BankDTO {
    public BankDTO(String name) {
        this.name = name;
    }

    private Long id;
    private String name;
}
