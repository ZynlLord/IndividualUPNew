package com.example.prac5up.repos;


import com.example.prac5up.models.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}
