package com.biblioteca2.biblioteca.service.impl;

import com.biblioteca2.biblioteca.model.Libro;
import com.biblioteca2.biblioteca.repository.LibroRepository;
import com.biblioteca2.biblioteca.service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    @Override
    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Libro obtenerPorId(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    @Override
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @Override
    public Libro actualizarLibro(Long id, Libro libro) {
        libro.setId(id);
        return libroRepository.save(libro);
    }

    @Override
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

    @Override
    public String subirPortada(MultipartFile file) {
        if (file.isEmpty()) return null;

        try {
            String carpeta = "uploads/"; // carpeta local para almacenar portadas
            File directorio = new File(carpeta);
            if (!directorio.exists()) directorio.mkdirs();

            String ruta = carpeta + file.getOriginalFilename();
            file.transferTo(new File(ruta));
            return ruta; // retorna la ruta donde se guardó la portada
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
