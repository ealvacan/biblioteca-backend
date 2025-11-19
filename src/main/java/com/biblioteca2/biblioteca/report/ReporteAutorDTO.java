package com.biblioteca2.biblioteca.report;

public class ReporteAutorDTO {

    private String nombreAutor;
    private Long cantidadLibros;
    private Double porcentaje;

    public ReporteAutorDTO(String nombreAutor, Long cantidadLibros, Double porcentaje) {
        this.nombreAutor = nombreAutor;
        this.cantidadLibros = cantidadLibros;
        this.porcentaje = porcentaje;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public Long getCantidadLibros() {
        return cantidadLibros;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }
}
