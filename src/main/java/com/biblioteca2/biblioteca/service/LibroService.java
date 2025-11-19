package com.biblioteca2.biblioteca.service;

import com.biblioteca2.biblioteca.model.Libro;

import java.util.List;

public interface LibroService {

    Libro crearLibro(Libro libro);

    Libro obtenerPorId(Long id);

    List<Libro> listarLibros();

    Libro actualizarLibro(Long id, Libro libro);

    void eliminarLibro(Long id);
}
