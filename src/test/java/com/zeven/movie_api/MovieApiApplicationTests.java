package com.zeven.movie_api;

import com.zeven.movie_api.services.MovieApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MovieApiApplicationTests {

    @Autowired
    private MovieApiService movieApiService;

    @Test
    void canGetMovieByName() {
        String movieResult = movieApiService.getMovieByName("Injection");
        System.out.println(movieResult);
        assertNotNull(movieResult);
    }

}
