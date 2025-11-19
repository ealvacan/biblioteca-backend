package com.biblioteca2.biblioteca.service.impl;

import com.biblioteca2.biblioteca.model.Autor;
import com.biblioteca2.biblioteca.repository.AutorRepository;
import com.biblioteca2.biblioteca.service.AutorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;

    public AutorServiceImpl(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public Autor crearAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public Autor obtenerPorId(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con id: " + id));
    }

    @Override
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    @Override
    public Autor actualizarAutor(Long id, Autor autor) {
        Autor existente = obtenerPorId(id);
        existente.setCedula(autor.getCedula());
        existente.setNombreCompleto(autor.getNombreCompleto());
        existente.setNacionalidad(autor.getNacionalidad());
        return autorRepository.save(existente);
    }

    @Override
    public void eliminarAutor(Long id) {
        autorRepository.deleteById(id);
    }
}
