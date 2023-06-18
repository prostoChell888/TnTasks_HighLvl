package ru.bahmutov.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
@ToString
@Setter
public class User {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "full_name")
    private String fullName;
}
