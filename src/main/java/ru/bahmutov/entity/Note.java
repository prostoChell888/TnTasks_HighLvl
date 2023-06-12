package ru.bahmutov.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notice")
class Note {

    @Id
    @Column(name = "notice_id")
    private Long id;

    @Column(name = "body")
    private  String body;

    @Column(name = "author")
    private String author;
}
