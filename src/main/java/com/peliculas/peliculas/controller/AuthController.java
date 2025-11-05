package com.peliculas.peliculas.controller;

import com.peliculas.peliculas.security.JwtUtil;
import com.peliculas.peliculas.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwt;

    public AuthController(UsuarioService usuarioService, JwtUtil jwt) {
        this.usuarioService = usuarioService;
        this.jwt = jwt;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> body){
        var u = usuarioService.registrar(body.get("username"), body.get("password"));
        return ResponseEntity.ok(Map.of("message","Usuario creado", "username", u.getUsername()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body){
        var u = usuarioService.validar(body.get("username"), body.get("password"));
        String token = jwt.generarToken(u.getUsername(), u.getRole());
        return ResponseEntity.ok(Map.of("token", token, "username", u.getUsername()));
    }
}