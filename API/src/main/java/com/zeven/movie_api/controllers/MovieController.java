package com.zeven.movie_api.controllers;

import com.zeven.movie_api.config.ApiPaths;
import com.zeven.movie_api.entities.FavouriteMovie;
import com.zeven.movie_api.services.MovieService;
import com.zeven.movie_api.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.PATH_MOVIES)
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = ApiPaths.PATH_LIST_MOVIE)
    public List<MovieDTO> getMoviesByName(@RequestParam final String movieName) {
        return movieService.getMoviesByName(movieName);
    }

    @GetMapping(value = ApiPaths.PATH_LIST_FAVOURITE)
    public List<FavouriteMovie> getFavouriteMovieList() {
        return movieService.getFavouriteMovieList();
    }

    @GetMapping(value = ApiPaths.PATH_SEARCH_FAVOURITE_BY_NAME)
    public MovieDTO getFavouriteMovieByTitle(@RequestParam final String movieTitle) {
        return movieService.getFavouriteMovieByName(movieTitle);
    }
    @PostMapping
    public ResponseEntity<Object> saveFavouriteMovie(@RequestBody final MovieDTO movieDTO) {
        return movieService.saveFavouriteMovie(movieDTO);
    }
    @DeleteMapping
    public void deleteFavouriteMovie(@RequestParam final String movieTitle) {
        movieService.deleteFavouriteMovie(movieTitle);
    }
}
