package com.peliculas.peliculas.controller;

import com.peliculas.peliculas.dto.PeliculaDto;
import com.peliculas.peliculas.entity.Favorita;
import com.peliculas.peliculas.service.FavoritaService;
import com.peliculas.peliculas.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/favoritas")
public class FavoritaController {

    @Autowired
    private FavoritaService favoritaService;

    @Autowired
    private PeliculaService peliculaService;

    // 🔹 Guardar una favorita directamente desde JSON
    @PostMapping
    public ResponseEntity<Favorita> guardar(@RequestBody Favorita favorita) {
        Favorita guardada = favoritaService.guardar(favorita);
        return ResponseEntity.ok(guardada);
    }

    // 🔹 Listar todas las favoritas
    @GetMapping
    public ResponseEntity<List<Favorita>> listar() {
        return ResponseEntity.ok(favoritaService.listarTodas());
    }

    // 🔹 Eliminar una favorita por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        favoritaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Agregar una película a favoritos desde la API externa
    @PostMapping("/agregar-por-titulo")
    public Mono<ResponseEntity<Favorita>> guardarPorTitulo(@RequestParam String titulo) {
        return peliculaService.buscarPorTitulo(titulo)
                .map(dto -> {
                    if (dto != null && dto.getTitle() != null) {
                        Favorita favorita = new Favorita();
                        favorita.setImdbId(dto.getImdbID());
                        favorita.setTitulo(dto.getTitle());
                        favorita.setDirector(dto.getDirector());
                        favorita.setAño(dto.getYear());
                        favorita.setPoster(dto.getPoster());
                        Favorita guardada = favoritaService.guardar(favorita);
                        return ResponseEntity.ok(guardada);
                    } else {
                        return ResponseEntity.notFound().build();
                    }
                });
    }
}