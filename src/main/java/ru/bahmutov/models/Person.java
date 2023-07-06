package ru.bahmutov.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
@Setter
@Getter
public class Person {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "full_name")
    private String fullName;
}
