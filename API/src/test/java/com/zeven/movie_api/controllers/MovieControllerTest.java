package com.zeven.movie_api.controllers;

import com.zeven.movie_api.config.ApiErrorMessage;
import com.zeven.movie_api.dto.MovieDTO;
import com.zeven.movie_api.entities.FavouriteMovie;
import com.zeven.movie_api.services.MovieService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


class MovieControllerTest {

    private AutoCloseable autoCloseable;

    private final String MOVIE_TITLE = "peli1";
    private final MovieDTO MOVIE_DTO = new MovieDTO();
    private final List<MovieDTO> MOVIES_LIST = new ArrayList<>();
    private final FavouriteMovie FAVORITE_MOVIE = new FavouriteMovie();
    private final Long FAVORITE_MOVIE_ID = 1L;
    private final List<FavouriteMovie> FAVORITE_MOVIES = new ArrayList<>();

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);

        MOVIE_DTO.setTitle(MOVIE_TITLE);
        MOVIES_LIST.add(MOVIE_DTO);
        FAVORITE_MOVIE.setTitle(MOVIE_TITLE);
        FAVORITE_MOVIE.setMovieId(FAVORITE_MOVIE_ID);
        FAVORITE_MOVIES.add(FAVORITE_MOVIE);
    }

    @Test
    void getMoviesByNameTest(){
        when(this.movieService.getMoviesByName(MOVIE_TITLE)).thenReturn(MOVIES_LIST);
        List<MovieDTO> response = this.movieController.getMoviesByName(MOVIE_TITLE);
        assertEquals(1, response.size());
        assertEquals(MOVIE_TITLE, response.get(0).getTitle());
    }

    @Test
    void getFavouriteMovieListTest() {
        when(this.movieService.getFavouriteMovieList()).thenReturn(FAVORITE_MOVIES);
        List<FavouriteMovie> response = this.movieController.getFavouriteMovieList();
        assertEquals(1, response.size());
    }

    @Test
    void getFavouriteMovieByTitleTest() {
        when(this.movieService.getFavouriteMovieByName(MOVIE_TITLE)).thenReturn(MOVIE_DTO);
        MovieDTO response = this.movieController.getFavouriteMovieByTitle(MOVIE_TITLE);
        assertEquals(MOVIE_TITLE, response.getTitle());
    }

    @Test
    void saveFavouriteMovieTest() {
        ResponseEntity<String> response = this.movieController.saveFavouriteMovie(MOVIE_DTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void deleteFavouriteMovieTest(){
        assertDoesNotThrow(() -> this.movieController.deleteFavouriteMovie(MOVIE_TITLE));
    }

    @Test
    void deleteFavouriteMovieWhenMovieNotExistTest(){
        doThrow(new RuntimeException(ApiErrorMessage.MOVIE_CANT_BE_DELETED))
                .when(this.movieService).deleteFavouriteMovie(MOVIE_TITLE);
        assertThrows(RuntimeException.class, () -> {
            this.movieController.deleteFavouriteMovie(MOVIE_TITLE);
        });
    }



    @AfterEach
    void close() throws Exception {
        autoCloseable.close();
    }
}
