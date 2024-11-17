package org.example.library.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookId implements Serializable {
    private String title;
    private String author;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        BookId bookId = (BookId) o;
//        return Objects.equals(title, bookId.title) && Objects.equals(author, bookId.author);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(title, author);
//    }
}

