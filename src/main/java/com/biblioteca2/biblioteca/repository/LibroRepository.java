package com.biblioteca2.biblioteca.repository;

import com.biblioteca2.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
