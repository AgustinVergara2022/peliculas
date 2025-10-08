package com.peliculas.peliculas.service;

import com.peliculas.peliculas.dto.PeliculaDto;
import com.peliculas.peliculas.entity.Favorita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PeliculaService {
    private final WebClient webClient;

    @Autowired
    public PeliculaService(WebClient webClient) { // Inyectás el que está en ConfigWebClient
        this.webClient = webClient;
    }

        public Mono<PeliculaDto> buscarPorTitulo(String titulo) {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("t", titulo)
                            .queryParam("apikey", "806e070a")
                            .build())
                    .retrieve()
                    .bodyToMono(PeliculaDto.class);
    }


    public Favorita convertirADominio(PeliculaDto dto) {
        Favorita favorita = new Favorita();
        favorita.setTitulo(dto.getTitle());
        favorita.setAño(dto.getYear());
        favorita.setDirector(dto.getDirector());
        favorita.setPoster(dto.getPoster());
        return favorita;
    }
}

