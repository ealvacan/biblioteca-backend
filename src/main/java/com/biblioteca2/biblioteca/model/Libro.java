package com.biblioteca2.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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

    // Precio del libro
    @Column(nullable = false)
    private BigDecimal precio;

    // Cantidad disponible
    @Column(nullable = false)
    private int cantidad;

    // URL de la portada (puede ser local o Cloudinary)
    private String portadaUrl;

    // Estado de disponibilidad para préstamo
    private boolean disponible;
}
