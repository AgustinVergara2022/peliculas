package com.peliculas.peliculas.repository;

import com.peliculas.peliculas.entity.Favorita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoritaRepository extends JpaRepository<Favorita, Long> {

    Optional<Favorita> findByImdbID(String imdbID);

    boolean existsByImdbID(String imdbID);

}
