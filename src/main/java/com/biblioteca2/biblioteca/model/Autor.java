package com.biblioteca2.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "autores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cedula;

    @Column(nullable = false)
    private String nombreCompleto;

    private String nacionalidad;
}
