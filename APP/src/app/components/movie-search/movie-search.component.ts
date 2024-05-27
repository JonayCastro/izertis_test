import { Component } from '@angular/core';
import {MovieService} from "../../services/movie-service/movie.service";
import {MovieVO} from "../../vos/MovieVO";
import {FormsModule} from "@angular/forms";
import {MovieListEmitterService} from "../../emiters/movie-list-emitter.service";

@Component({
  selector: 'app-movie-search',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './movie-search.component.html',
  styleUrls: ['./movie-search.component.css']
})
export class MovieSearchComponent {

  private movieList: MovieVO[] = []
  public movieName: string = ''

  constructor(private movieService: MovieService, private movieListEmitterService: MovieListEmitterService) {}

  searchMovies():void{
    if(this.movieName != ''){
      this.movieService.getMovies(this.movieName).subscribe((response: MovieVO[]) =>{
        if(response.length != 0){
          this.movieList = response
          this.movieListEmitterService.movieListLoaded(this.movieList)
        }
      })
      this.movieName = ''
    }
  }
}
