package org.example.library.repository;

import org.example.library.entity.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<LibraryUser, String> {
    LibraryUser findByFullName(String fullname);
}
