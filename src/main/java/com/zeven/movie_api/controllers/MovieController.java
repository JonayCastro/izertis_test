package com.zeven.movie_api.controllers;

import com.zeven.movie_api.config.ApiPaths;
import com.zeven.movie_api.services.MovieService;
import com.zeven.movie_api.vo.MovieVO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public MovieVO saveMovie(@RequestBody final MovieVO movieVO) {
        return null;
    }
}
