package com.biblioteca2.biblioteca.repository;

import com.biblioteca2.biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByUsername(String username);

    Usuario findByUsername(String username);
}
