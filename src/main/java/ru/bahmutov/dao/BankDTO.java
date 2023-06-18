package ru.bahmutov.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class BankDTO {
    private Long id;
    private String name;
}
