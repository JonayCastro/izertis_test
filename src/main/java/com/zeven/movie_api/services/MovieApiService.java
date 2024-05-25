package com.zeven.movie_api.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieApiService {

    private final WebClient webClient;

    @Value("${movie.api.access.key}")
    private String apiKey;


    @Autowired
    public MovieApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String getMovieByName(String movieName){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("query", movieName)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
