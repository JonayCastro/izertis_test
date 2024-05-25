package com.zeven.movie_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zeven.movie_api.services.MovieApiService;
import com.zeven.movie_api.vo.MovieSearchVO;
import com.zeven.movie_api.vo.MovieVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MovieApiApplicationTests {

    @Autowired
    private MovieApiService movieApiService;

    @Test
    void canSearchMovieByName() throws JsonProcessingException {
        MovieSearchVO movieResult = movieApiService.getMovieByName("Injection");
        System.out.println(movieResult);
        assertNotNull(movieResult);
    }

    @Test
    void canGetMovieList(){
        List<MovieVO> movies = movieApiService.movieList();
        System.out.println(movies.size());
    }

}
