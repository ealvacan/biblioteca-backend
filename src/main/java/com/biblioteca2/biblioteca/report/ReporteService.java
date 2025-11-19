package com.biblioteca2.biblioteca.report;

import com.biblioteca2.biblioteca.model.Libro;
import com.biblioteca2.biblioteca.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    private final LibroRepository libroRepository;

    public ReporteService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public String generarReporteXML() {

        List<Libro> libros = libroRepository.findAll();
        int totalLibros = libros.size();

        Map<String, Long> conteoPorAutor =
                libros.stream().collect(
                        Collectors.groupingBy(l -> l.getAutor().getNombreCompleto(), Collectors.counting())
                );

        StringBuilder xml = new StringBuilder();
        xml.append("<reporte>\n");
        xml.append("   <totalLibros>").append(totalLibros).append("</totalLibros>\n");
        xml.append("   <autores>\n");

        conteoPorAutor.forEach((autor, cantidad) -> {
            double porcentaje = totalLibros > 0 ? (cantidad * 100.0 / totalLibros) : 0;

            xml.append("       <autor nombre=\"").append(autor)
                    .append("\" porcentaje=\"").append(String.format("%.2f", porcentaje)).append("\">")
                    .append(cantidad)
                    .append("</autor>\n");
        });

        xml.append("   </autores>\n");
        xml.append("</reporte>");

        return xml.toString();
    }
}
