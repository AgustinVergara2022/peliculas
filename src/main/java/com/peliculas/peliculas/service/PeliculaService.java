/*package com.peliculas.peliculas.service;

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
}*/
package com.peliculas.peliculas.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.peliculas.peliculas.dto.PeliculaDto;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;


@Service
public class PeliculaService {

    private static final Logger logger = LoggerFactory.getLogger(PeliculaService.class);

    private final WebClient webClient = WebClient.create();

    @Value("${omdb.api.url}")
    private String apiUrl;

    @Value("${omdb.api.key}")
    private String apiKey;



    public Mono<List<PeliculaDto>> buscarPorTitulo(String titulo) {
        String url = "https://www.omdbapi.com/?apikey=" + apiKey + "&s=" + titulo;

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> {
                    List<PeliculaDto> resultados = new ArrayList<>();

                    if (json.has("Search")) {
                        for (JsonNode item : json.get("Search")) {
                            PeliculaDto dto = new PeliculaDto();
                            dto.setImdbID(item.path("imdbID").asText());
                            dto.setTitle(item.path("Title").asText());
                            dto.setYear(item.path("Year").asText());
                            dto.setPoster(item.path("Poster").asText());
                            resultados.add(dto);
                        }
                    }

                    return resultados;
                });
    }

    public Mono<PeliculaDto> obtenerDetallePorId(String id) {
        String url = String.format("%s?apikey=%s&i=%s", apiUrl, apiKey, id);
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(PeliculaDto.class)
                .doOnNext(pelicula -> logger.info("Detalle obtenido para {}: {}", id, pelicula.getTitle()))
                .onErrorResume(e -> {
                    logger.error("Error obteniendo detalle de película con ID {}: {}", id, e.getMessage());
                    return Mono.empty();
                });
    }

}


