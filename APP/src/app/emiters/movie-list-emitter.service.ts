import {EventEmitter, Injectable, Output} from '@angular/core';
import {MovieVO} from "../vos/MovieVO";

@Injectable({
  providedIn: 'root'
})
export class MovieListEmitterService {

  @Output() movieListEmitter: EventEmitter<MovieVO[]> = new EventEmitter()
  @Output() movieAddFavouriteEmitter: EventEmitter<MovieVO> = new EventEmitter()

  movieListLoaded(movieList: MovieVO[]): void{
    this.movieListEmitter.emit(movieList)
  }

  movieAddedToFavourite(movie: MovieVO): void{
    this.movieAddFavouriteEmitter.emit(movie)
  }

}
