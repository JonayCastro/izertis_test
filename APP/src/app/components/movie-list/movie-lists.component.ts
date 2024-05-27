import { Component } from '@angular/core';
import {MovieVO} from "../../vos/MovieVO";
import {MovieListEmitterService} from "../../emiters/movie-list-emitter.service";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {MovieService} from "../../services/movie-service/movie.service";

@Component({
  selector: 'app-movie-lists',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './movie-lists.component.html',
  styleUrls: ['./movie-lists.component.css']
})
export class MovieListsComponent {

  public movieList: MovieVO[] = []

  constructor(private movieListEmitterService: MovieListEmitterService, private movieService: MovieService) {
    this.loadList()
  }

  loadList(){
    this.movieListEmitterService.movieListEmitter.subscribe((list: MovieVO[]): void => {
      this.movieList = list
    })
  }

  addToFavourites(movie: MovieVO){
    this.movieService.addMovieToFavouritesList(movie).subscribe((movie: MovieVO) => {
      if(movie){
        this.movieListEmitterService.movieAddedToFavourite(movie)
      }
    })
  }

}
