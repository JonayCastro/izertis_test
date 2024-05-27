package com.zeven.movie_api.mappers;

import com.zeven.movie_api.entities.FavouriteMovie;
import com.zeven.movie_api.vo.MovieVO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MovieMapper {

    FavouriteMovie movieVOToFavouriteMovie(MovieVO movieVO);
    MovieVO favouriteMovieToMovieVO(FavouriteMovie favouriteMovie);
}
