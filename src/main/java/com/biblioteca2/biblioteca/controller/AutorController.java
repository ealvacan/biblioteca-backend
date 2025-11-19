package com.biblioteca2.biblioteca.controller;

import com.biblioteca2.biblioteca.model.Autor;
import com.biblioteca2.biblioteca.service.AutorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
@CrossOrigin(origins = "*")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping
    public Autor crear(@RequestBody Autor autor) {
        return autorService.crearAutor(autor);
    }

    @GetMapping("/{id}")
    public Autor obtener(@PathVariable Long id) {
        return autorService.obtenerPorId(id);
    }

    @GetMapping
    public List<Autor> listar() {
        return autorService.listarAutores();
    }

    @PutMapping("/{id}")
    public Autor actualizar(@PathVariable Long id, @RequestBody Autor autor) {
        return autorService.actualizarAutor(id, autor);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        autorService.eliminarAutor(id);
    }
}
