package com.peliculas.peliculas.config;

import com.peliculas.peliculas.entity.Usuario;
import com.peliculas.peliculas.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminSeeder {

    @Bean
    public CommandLineRunner initAdmin(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        return args -> {
            String adminUsername = "admin";

            // Verifica si el usuario admin ya existe
            if (usuarioRepository.findByUsername(adminUsername).isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername(adminUsername);
                admin.setPasswordHash(encoder.encode("admin123")); // 🔒 Hash automático
                admin.setRole("ADMIN");
                usuarioRepository.save(admin);

                System.out.println("✅ Usuario admin creado automáticamente (admin / admin123)");
            } else {
                System.out.println("ℹ️ Usuario admin ya existe, no se volvió a crear.");
            }
        };
    }
}
