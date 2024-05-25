package com.zeven.movie_api.mappers;

import com.zeven.movie_api.entities.Movie;
import com.zeven.movie_api.vo.MovieVO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MovieMapper {

    Movie movieVOToMovie(MovieVO movieVO);
    MovieVO movieVOToMovieVO(Movie movie);
}
