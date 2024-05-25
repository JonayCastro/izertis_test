package com.zeven.movie_api.daos;

import com.zeven.movie_api.entities.FavouriteMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "favouriteMovieDao")
public interface FavouriteMovieDao extends JpaRepository<FavouriteMovie, Long> {
}
