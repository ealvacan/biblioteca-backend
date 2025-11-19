package com.biblioteca2.biblioteca.service;

import com.biblioteca2.biblioteca.model.Autor;

import java.util.List;

public interface AutorService {

    Autor crearAutor(Autor autor);

    Autor obtenerPorId(Long id);

    List<Autor> listarAutores();

    Autor actualizarAutor(Long id, Autor autor);

    void eliminarAutor(Long id);
}
