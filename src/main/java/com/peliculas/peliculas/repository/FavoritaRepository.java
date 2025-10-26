package com.peliculas.peliculas.repository;

import com.peliculas.peliculas.entity.Favorita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavoritaRepository extends JpaRepository<Favorita, Long> {

    Optional<Favorita> findByImdbID(String imdbID);

    boolean existsByImdbID(String imdbID);

    @Query("SELECT f FROM Favorita f WHERE CAST(f.anio AS integer) >= :anio")
    List<Favorita> findByAnio(@Param("anio") String anio);

    @Query("SELECT f FROM Favorita f WHERE f.anio IS NOT NULL ORDER BY CAST(f.anio AS integer) ASC")
    List<Favorita> findAllOrderByAnioNumerico();
}
