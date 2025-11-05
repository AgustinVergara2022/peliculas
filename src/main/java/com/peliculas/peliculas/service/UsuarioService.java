package com.peliculas.peliculas.service;

import com.peliculas.peliculas.entity.Usuario;
import com.peliculas.peliculas.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;
    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public Usuario registrar(String username, String password) {
        if (repo.existsByUsername(username)) throw new IllegalArgumentException("Usuario existente");
        Usuario u = new Usuario();
        u.setUsername(username);
        u.setPasswordHash(encoder.encode(password));
        return repo.save(u);
    }

    public Usuario validar(String username, String rawPassword) {
        Usuario u = repo.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));
        if (!encoder.matches(rawPassword, u.getPasswordHash())) throw new IllegalArgumentException("Credenciales inválidas");
        return u;
    }
}
