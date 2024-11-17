package org.example.library.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
public class LibraryUser {
    @Id
    private String username;
    private String fullName;
    private boolean active;
    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private List<Book> books = new ArrayList<>();

    public LibraryUser() {}
}
