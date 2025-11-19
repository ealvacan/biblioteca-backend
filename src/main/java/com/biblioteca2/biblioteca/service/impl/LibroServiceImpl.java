package com.biblioteca2.biblioteca.service.impl;

import com.biblioteca2.biblioteca.model.Autor;
import com.biblioteca2.biblioteca.model.Libro;
import com.biblioteca2.biblioteca.repository.AutorRepository;
import com.biblioteca2.biblioteca.repository.LibroRepository;
import com.biblioteca2.biblioteca.service.LibroService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public LibroServiceImpl(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    @Override
    public Libro crearLibro(Libro libro) {

        // Validar que el autor exista antes de asignarlo
        Autor autor = autorRepository.findById(libro.getAutor().getId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        libro.setAutor(autor);

        return libroRepository.save(libro);
    }

    @Override
    public Libro obtenerPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id));
    }

    @Override
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @Override
    public Libro actualizarLibro(Long id, Libro libro) {
        Libro existente = obtenerPorId(id);

        existente.setTitulo(libro.getTitulo());
        existente.setIsbn(libro.getIsbn());
        existente.setAnio(libro.getAnio());

        Autor autor = autorRepository.findById(libro.getAutor().getId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        existente.setAutor(autor);

        return libroRepository.save(existente);
    }

    @Override
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }
}
