package com.zeven.movie_api.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeven.movie_api.vo.MovieSearchVO;
import com.zeven.movie_api.vo.MovieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class MovieApiService {

    private final WebClient webClient;
    private final ObjectMapper mapper;

    @Value("${movie.api.access.key}")
    private String apiKey;


    @Autowired
    public MovieApiService(WebClient webClient, ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.mapper = objectMapper;
    }

    public MovieSearchVO getMovieByName(String movieName) throws JsonProcessingException {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("query", movieName)
                        .build())
                .retrieve()
                .bodyToMono(MovieSearchVO.class)
                .block();
    }

    public List<MovieVO> movieList(){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("api_key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(MovieSearchVO.class)
                .map(MovieSearchVO::getMoviesVOs)
                .block();
    }

}
