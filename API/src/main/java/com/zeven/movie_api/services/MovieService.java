package com.zeven.movie_api.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeven.movie_api.config.ApiErrorMessage;
import com.zeven.movie_api.daos.FavouriteMovieDao;
import com.zeven.movie_api.dto.MovieDTO;
import com.zeven.movie_api.dto.MovieSearchDTO;
import com.zeven.movie_api.entities.FavouriteMovie;
import com.zeven.movie_api.mappers.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

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

    public List<MovieDTO> getMoviesByName(final String movieName){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("query", movieName)
                        .build())
                .retrieve()
                .bodyToMono(MovieSearchDTO.class)
                .map(MovieSearchDTO::getMoviesVOs)
                .block();
    }

    public ResponseEntity<Object> saveFavouriteMovie(final MovieDTO movieDTO){
        FavouriteMovie favouriteMovie = mapper.movieVOToFavouriteMovie(movieDTO);
        ResponseEntity<Object> response = new ResponseEntity<>(favouriteMovie, HttpStatus.CREATED);
        try{
            favouriteMovieDao.save(favouriteMovie);
        } catch (DataIntegrityViolationException exception){
            response = new ResponseEntity<>(ApiErrorMessage.FAVOURITE_MOVIE_ALREADY_EXISTS, HttpStatus.CONFLICT);
        } catch (Exception exception){
            String errorMessage = ApiErrorMessage.ANY_EXCEPTION + exception.getMessage();
            response = new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public List<FavouriteMovie> getFavouriteMovieList(){
        return favouriteMovieDao.findAll();
    }

    public MovieDTO getFavouriteMovieByName(final String movieTitle){
        FavouriteMovie favouriteMovie = favouriteMovieDao.findFavouriteMovieByTitle(movieTitle)
                .orElseThrow(() -> new RuntimeException(ApiErrorMessage.MOVIE_NOT_FOUND));
        return mapper.favouriteMovieToMovieVO(favouriteMovie);
    }

    public void deleteFavouriteMovie(final String movieTitle){
        favouriteMovieDao.findFavouriteMovieByTitle(movieTitle)
                .orElseThrow(() -> new RuntimeException(ApiErrorMessage.MOVIE_CANT_BE_DELETED));
    }

}
