package com.zeven.movie_api.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeven.movie_api.config.ApiErrorMessage;
import com.zeven.movie_api.daos.FavouriteMovieDao;
import com.zeven.movie_api.entities.FavouriteMovie;
import com.zeven.movie_api.mappers.MovieMapper;
import com.zeven.movie_api.vo.MovieSearchVO;
import com.zeven.movie_api.vo.MovieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    private final FavouriteMovieDao favouriteMovieDao;
    private final MovieMapper mapper;

    @Value("${movie.api.access.key}")
    private String apiKey;


    @Autowired
    public MovieService(final WebClient webClient, final ObjectMapper objectMapper, final FavouriteMovieDao favouriteMovieDao, final MovieMapper mapper) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;
        this.favouriteMovieDao = favouriteMovieDao;
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

    public ResponseEntity<MovieVO> saveFavouriteMovie(final MovieVO movieVO){
        FavouriteMovie favouriteMovie = mapper.movieVOToFavouriteMovie(movieVO);
        favouriteMovieDao.save(favouriteMovie);
        return new ResponseEntity<>(mapper.favouriteMovieToMovieVO(favouriteMovie), HttpStatus.CREATED);
    }

    public ResponseEntity<List<FavouriteMovie>> getFavouriteMovieList(){
        List<FavouriteMovie> favouriteMovies = favouriteMovieDao.findAll();
        return new ResponseEntity<>(favouriteMovies, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Object> getFavouriteMovieByName(final String movieTitle){
        ResponseEntity<Object> response = new ResponseEntity<>(ApiErrorMessage.FAVOURITE_MOVIE_NOT_FOUND, HttpStatus.NOT_FOUND);
        FavouriteMovie favouriteMovie = favouriteMovieDao.findFavouriteMovieByTitle(movieTitle).orElse(null);
        if(favouriteMovie != null){
            MovieVO movieVO = mapper.favouriteMovieToMovieVO(favouriteMovie);
            response = new ResponseEntity<>(movieVO, HttpStatus.ACCEPTED);
        }
        return response;
    }

}
