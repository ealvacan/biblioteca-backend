package com.biblioteca2.biblioteca.repository;

import com.biblioteca2.biblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
