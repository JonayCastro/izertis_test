package com.zeven.movie_api.daos;

import com.zeven.movie_api.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "movieDao")
public interface MovieDao extends JpaRepository<Movie, Long> {
}
