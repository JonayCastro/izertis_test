package com.zeven.movie_api.mappers;

import com.zeven.movie_api.dto.MovieDTO;
import com.zeven.movie_api.entities.FavouriteMovie;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MovieMapper {

    FavouriteMovie movieVOToFavouriteMovie(MovieDTO movieDTO);
    MovieDTO favouriteMovieToMovieVO(FavouriteMovie favouriteMovie);
}
