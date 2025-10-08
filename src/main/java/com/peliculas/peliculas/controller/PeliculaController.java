package com.peliculas.peliculas.controller;

import com.peliculas.peliculas.dto.PeliculaDto;
import com.peliculas.peliculas.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

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
