package com.biblioteca2.biblioteca.controller;

import com.biblioteca2.biblioteca.model.Libro;
import com.biblioteca2.biblioteca.service.LibroService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "*")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public Libro crear(@RequestBody Libro libro) {
        return libroService.crearLibro(libro);
    }

    @GetMapping("/{id}")
    public Libro obtener(@PathVariable Long id) {
        return libroService.obtenerPorId(id);
    }

    @GetMapping
    public List<Libro> listar() {
        return libroService.listarLibros();
    }

    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id, @RequestBody Libro libro) {
        return libroService.actualizarLibro(id, libro);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        libroService.eliminarLibro(id);
    }

    @PostMapping("/bulk")
    public List<Libro> crearVarios(@RequestBody List<Libro> libros) {
        return libros.stream()
                .map(libroService::crearLibro)
                .toList();
    }

    // NUEVO: Subida de portada
    @PostMapping("/{id}/portada")
    public String subirPortada(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return libroService.subirPortada(file);
    }

    // NUEVO: Prestar libro
    @PostMapping("/{id}/prestar")
    public Libro prestar(@PathVariable Long id) {
        return libroService.prestar(id);
    }
}
