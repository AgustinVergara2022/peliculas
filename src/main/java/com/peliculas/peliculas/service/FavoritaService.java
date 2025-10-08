package com.peliculas.peliculas.service;

import com.peliculas.peliculas.entity.Favorita;
import com.peliculas.peliculas.repository.FavoritaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FavoritaService {

    @Autowired
    private FavoritaRepository favoritaRepository;

    public Favorita guardar(Favorita favorita) {
        boolean existe = favoritaRepository.findByImdbId(favorita.getImdbId()).isPresent();
        if (existe) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La película ya está en favoritos");
        }

        return favoritaRepository.save(favorita);
    }

    public List<Favorita> listarTodas() {
        return favoritaRepository.findAll();
    }

    public void eliminar(Long id) {
        favoritaRepository.deleteById(id);
    }

}
