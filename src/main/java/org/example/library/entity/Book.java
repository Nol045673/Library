package org.example.library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@IdClass(BookId.class)
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private String title;
    @Id
    private String author;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "username")
    @JsonBackReference
    private LibraryUser user;
}
