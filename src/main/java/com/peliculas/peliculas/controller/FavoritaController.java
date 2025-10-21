package com.peliculas.peliculas.controller;

import com.peliculas.peliculas.dto.PeliculaDto;
import com.peliculas.peliculas.entity.Favorita;
import com.peliculas.peliculas.service.FavoritaService;
import com.peliculas.peliculas.service.PeliculaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/favoritas")
@CrossOrigin(value = "http://localhost:4200")
public class FavoritaController {
    private static final Logger Logger= LoggerFactory.getLogger(PeliculaController.class);

    @Autowired
    private FavoritaService favoritaService;

    @Autowired
    private PeliculaService peliculaService;

    @PostMapping
    public ResponseEntity<Favorita> guardar(@RequestBody Favorita favorita) {
        Favorita guardada = favoritaService.guardar(favorita);
        return ResponseEntity.ok(guardada);
    }


    @GetMapping
    public ResponseEntity<List<Favorita>> listar() {
        List<Favorita> favoritas = favoritaService.listarTodas();
        Logger.info("Películas favoritas encontradas: ");
        favoritas.forEach(favorita -> Logger.info(favorita.toString()));
        return ResponseEntity.ok(favoritas);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        favoritaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/agregar-por-titulo")
    public Mono<ResponseEntity<Favorita>> guardarPorTitulo(@RequestParam String titulo) {
        return peliculaService.buscarPorTitulo(titulo)
                .map(lista -> {
                    if (lista != null && !lista.isEmpty()) {
                        PeliculaDto dto = lista.get(0); // usar el primer resultado (ajustar si necesitas otra lógica)
                        if (dto != null && dto.getTitle() != null) {
                            Favorita favorita = new Favorita();
                            favorita.setImdbID(dto.getImdbID());
                            favorita.setTitulo(dto.getTitle());
                            favorita.setDirector(dto.getDirector());
                            favorita.setAño(dto.getYear());
                            favorita.setPoster(dto.getPoster());
                            Favorita guardada = favoritaService.guardar(favorita);
                            return ResponseEntity.ok(guardada);
                        }
                    }
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<Favorita> actualizarFavorita(@PathVariable Long id, @RequestBody Favorita favorita) {
        Favorita existente = favoritaService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        // Solo se actualizan los campos modificables
        existente.setComentario(favorita.getComentario());
        existente.setPuntuacion(favorita.getPuntuacion());

        Favorita actualizada = favoritaService.actualizar(existente);
        return ResponseEntity.ok(actualizada);
    }


}