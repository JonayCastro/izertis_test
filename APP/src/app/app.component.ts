import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {MovieSearchComponent} from "./components/movie-search/movie-search.component";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {MovieListsComponent} from "./components/movie-list/movie-lists.component";
import {FavouriteMovieListComponent} from "./components/favourite-movie-list/favourite-movie-list.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: true,
  imports: [
    MovieSearchComponent, HttpClientModule, MovieListsComponent, FavouriteMovieListComponent
  ],
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'movie-api-client';
}
