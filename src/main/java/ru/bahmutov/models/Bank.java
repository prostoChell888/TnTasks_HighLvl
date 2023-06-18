package ru.bahmutov.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bank")
@ToString
@Setter
public class Bank {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
}
