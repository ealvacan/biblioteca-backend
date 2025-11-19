package com.biblioteca2.biblioteca.service.impl;

import com.biblioteca2.biblioteca.model.Libro;
import com.biblioteca2.biblioteca.repository.LibroRepository;
import com.biblioteca2.biblioteca.service.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public Libro crearLibro(Libro libro) {
        Libro l = libroRepository.save(libro);
        messagingTemplate.convertAndSend("/topic/libros", l);
        return l;
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
        Libro l = libroRepository.save(libro);
        messagingTemplate.convertAndSend("/topic/libros", l);
        return l;
    }

    @Override
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
        messagingTemplate.convertAndSend("/topic/libros", new Libro(id));
    }

    @Override
    public String subirPortada(MultipartFile file) {
        if (file.isEmpty()) return null;

        try {
            String carpeta = "uploads/";
            File directorio = new File(carpeta);
            if (!directorio.exists()) directorio.mkdirs();

            String ruta = carpeta + file.getOriginalFilename();
            file.transferTo(new File(ruta));
            return ruta;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Libro prestar(Long id) {
        Libro libro = libroRepository.findById(id).orElseThrow();
        if (!libro.isDisponible()) throw new RuntimeException("Libro no disponible");
        libro.setDisponible(false);
        libroRepository.save(libro);
        messagingTemplate.convertAndSend("/topic/libros", libro);
        return libro;
    }
}
