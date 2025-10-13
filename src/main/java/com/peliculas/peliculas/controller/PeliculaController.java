package com.peliculas.peliculas.controller;

import com.peliculas.peliculas.dto.PeliculaDto;
import com.peliculas.peliculas.service.PeliculaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin(value = "http://localhost:4200")
public class PeliculaController {
    private static final Logger Logger= LoggerFactory.getLogger(PeliculaController.class);

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping("/buscar")
    public Mono<ResponseEntity<PeliculaDto>> buscarPorTitulo(@RequestParam String titulo) {
        return peliculaService.buscarPorTitulo(titulo)
                .map(pelicula -> {
                    if (pelicula.getTitle() != null) {
                        return ResponseEntity.ok(pelicula);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                });
    }


}
