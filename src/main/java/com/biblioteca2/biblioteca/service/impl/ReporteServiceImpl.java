package com.biblioteca2.biblioteca.service.impl;

import com.biblioteca2.biblioteca.model.Libro;
import com.biblioteca2.biblioteca.repository.LibroRepository;
import com.biblioteca2.biblioteca.service.ReporteService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final LibroRepository libroRepository;

    @Override
    public String generarXML() throws Exception {
        List<Libro> libros = libroRepository.findAll();
        BigDecimal total = libros.stream()
                .map(l -> l.getPrecio().multiply(new BigDecimal(l.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<Map<String, Object>> librosMap = libros.stream().map(l -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", l.getId());
            map.put("titulo", l.getTitulo());
            map.put("precio", l.getPrecio());
            map.put("cantidad", l.getCantidad());
            BigDecimal subtotal = l.getPrecio().multiply(new BigDecimal(l.getCantidad()));
            map.put("subtotal", subtotal);
            map.put("porcentaje", subtotal.multiply(new BigDecimal("100"))
                    .divide(total, 2, RoundingMode.HALF_UP));
            return map;
        }).toList();

        Map<String, Object> reporte = new HashMap<>();
        reporte.put("total", total);
        reporte.put("libros", librosMap);

        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writeValueAsString(reporte);
    }
}
