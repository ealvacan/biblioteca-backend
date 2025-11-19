package com.biblioteca2.biblioteca.controller;

import com.biblioteca2.biblioteca.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReporteController {

    private final ReporteService reporteService;

    @GetMapping(value = "/libros/xml", produces = "application/xml")
    public String getReporteXML() throws Exception {
        return reporteService.generarXML();
    }
}
