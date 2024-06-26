package com.zeven.movie_api;

import com.zeven.movie_api.services.MovieService;
import com.zeven.movie_api.dto.MovieDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FavouriteMovieApiApplicationTests {

    @Autowired
    private MovieService movieService;


    @Test
    void canGetMovieList(){
        List<MovieDTO> movies = movieService.getMoviesByName("Injection");
        assertNotNull(movies);
    }

}
