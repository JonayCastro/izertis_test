package com.zeven.movie_api.controllers;

import com.zeven.movie_api.config.ApiPaths;
import com.zeven.movie_api.entities.FavouriteMovie;
import com.zeven.movie_api.services.MovieService;
import com.zeven.movie_api.vo.MovieVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = ApiPaths.PATH_MOVIES + ApiPaths.PATH_LIST_MOVIE)
    public List<MovieVO> getMoviesByName(@RequestParam final String movieName) {
        return movieService.getMoviesByName(movieName);
    }

    @PostMapping(value = ApiPaths.PATH_MOVIES + ApiPaths.PATH_SAVE_MOVIE)
    public ResponseEntity<Object> saveFavouriteMovie(@RequestBody final MovieVO movieVO) {
        return movieService.saveFavouriteMovie(movieVO);
    }

    @GetMapping(value = ApiPaths.PATH_MOVIES + ApiPaths.PATH_FAVOURITE_MOVIE_LIST)
    public ResponseEntity<List<FavouriteMovie>> getFavouriteMovieList() {
        return movieService.getFavouriteMovieList();
    }

    @GetMapping(value = ApiPaths.PATH_MOVIES + ApiPaths.PATH_FAVOURITE_MOVIE_BY_NAME)
    public ResponseEntity<Object> getFavouriteMovieByTitle(@RequestParam final String movieTitle) {
        return movieService.getFavouriteMovieByName(movieTitle);
    }

    @DeleteMapping(value = ApiPaths.PATH_MOVIES + ApiPaths.PATH_DELETE_OF_FAVOURITE)
    public ResponseEntity<Object> deleteFavouriteMovie(@RequestParam final String movieTitle) {
        return movieService.deleteFavouriteMovie(movieTitle);
    }
}