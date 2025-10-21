package com.peliculas.peliculas.service;

import com.peliculas.peliculas.entity.Favorita;
import com.peliculas.peliculas.exception.FavoritaExistenteException;
import com.peliculas.peliculas.repository.FavoritaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavoritaService {

    @Autowired
    private FavoritaRepository favoritaRepository;

    public Favorita guardar(Favorita favorita) {
        // Evitar duplicados por imdbID
        if (favorita.getImdbID() != null && favoritaRepository.existsByImdbID(favorita.getImdbID())) {
            throw new FavoritaExistenteException("La película ya está en favoritos");
        }

        // Asignar fecha actual si no fue enviada
        if (favorita.getFechaGuardado() == null) {
            favorita.setFechaGuardado(LocalDateTime.now());
        }

        return favoritaRepository.save(favorita);
    }

    public List<Favorita> listarTodas() {
        return favoritaRepository.findAll();
    }

    public void eliminar(Long id) {
        favoritaRepository.deleteById(id);
    }

    public Favorita buscarPorId(Long id) {
        return favoritaRepository.findById(id).orElse(null);
    }

    public Favorita actualizar(Favorita favorita) {
        return favoritaRepository.save(favorita);
    }


}
