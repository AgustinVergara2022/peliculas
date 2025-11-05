package com.peliculas.peliculas.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/sobre-mi")
@CrossOrigin(value = "http://localhost:4200")
public class SobreMiController {

    @GetMapping
    public Map<String, Object> obtenerSobreMi() {
        return Map.of(
                "nombre", "Agustín Vergara",
                "profesion", "Estudiante de Ingeniería en Sistemas de Información (UTN)",
                "descripcion", "Apasionado por el desarrollo backend con Java, Spring Boot y bases de datos relacionales. Actualmente creando MovieTracker, una app fullstack para gestionar películas favoritas.",
                "correo", "agusvergara@gmail.com",
                "linkedin", "https://www.linkedin.com/in/agustinvergara",
                "github", "https://github.com/agustinvergara",
                "telefono", "+54 9 11 2345-6789"
        );
    }
}
