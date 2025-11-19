package com.biblioteca2.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "libros")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String isbn;

    private int anio;

    // Relación con Autor
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
}
