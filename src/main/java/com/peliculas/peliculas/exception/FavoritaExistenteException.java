package com.peliculas.peliculas.exception;

public class FavoritaExistenteException extends RuntimeException {
    public FavoritaExistenteException(String mensaje) {
        super(mensaje);
    }
}
