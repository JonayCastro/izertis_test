import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {MovieVO} from "../../vos/MovieVO";
import {MovieService} from "../../services/movie-service/movie.service";
import {MovieListEmitterService} from "../../emiters/movie-list-emitter.service";

@Component({
  selector: 'app-favourite-movie-list',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './favourite-movie-list.component.html',
  styleUrls: ['./favourite-movie-list.component.css']
})
export class FavouriteMovieListComponent {
  public favouriteMovieList: MovieVO[] = [];

  constructor(private movieListEmitterService: MovieListEmitterService, private movieService: MovieService) {
    this.addToFavouriteObserver()
    this.loadFavouritesList()
  }

  loadFavouritesList(){
    this.movieService.getFavouriteList().subscribe((favouriteLis: MovieVO[]): void =>{
      this.favouriteMovieList = favouriteLis
    })
  }

  addToFavouriteObserver(): void{
    this.movieListEmitterService.movieAddFavouriteEmitter.subscribe((movie: MovieVO) => {
      this.movieService.getFavouriteList().subscribe((movieList: MovieVO[]): void => {
        if(movieList.length != 0){
          this.favouriteMovieList = movieList
        }
      })
    })
  }

  deleteMovie(movie: MovieVO){
    const movieTitle: string | null = movie.title
    if(movieTitle){
      this.movieService.deleteMovieInFavourite(movie.title).subscribe((result: boolean): void => {
        if(result){
          this.loadFavouritesList()
        }
      })
    }
  }

}
