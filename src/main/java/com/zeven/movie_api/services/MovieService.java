package com.zeven.movie_api.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeven.movie_api.daos.MovieDao;
import com.zeven.movie_api.mappers.MovieMapper;
import com.zeven.movie_api.vo.MovieSearchVO;
import com.zeven.movie_api.vo.MovieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class MovieService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    private final MovieDao movieDao;
    private final MovieMapper mapper;

    @Value("${movie.api.access.key}")
    private String apiKey;


    @Autowired
    public MovieService(final WebClient webClient, final ObjectMapper objectMapper, final MovieDao movieDao, final MovieMapper mapper) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;
        this.movieDao = movieDao;
        this.mapper = mapper;
    }

    public List<MovieVO> getMoviesByName(final String movieName){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("query", movieName)
                        .build())
                .retrieve()
                .bodyToMono(MovieSearchVO.class)
                .map(MovieSearchVO::getMoviesVOs)
                .block();
    }

}
