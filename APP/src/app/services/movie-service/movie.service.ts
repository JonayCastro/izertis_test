import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {AppPaths} from "../../config/AppPaths";
import {Observable} from "rxjs";
import {MovieVO} from "../../vos/MovieVO";

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private baseUrl: string = AppPaths.BASE_URL;


  constructor(private http: HttpClient) {}

  getMovies(movieName: string): Observable<MovieVO[]>{
    const path: string = this.baseUrl + AppPaths.PATH_LIST_MOVIE
    let params: HttpParams = new HttpParams().set('movieName', movieName)
    return this.http.get<MovieVO[]>(path, {params: params})
  }

  addMovieToFavouritesList(movie: MovieVO): Observable<MovieVO> {
    const path: string = this.baseUrl + AppPaths.PATH_SAVE_MOVIE
    return this.http.post<MovieVO>(path, movie)
  }

  getFavouriteList(): Observable<MovieVO[]>{
    const path: string = this.baseUrl + AppPaths.PATH_FAVOURITE_MOVIE_LIST
    return this.http.get<MovieVO[]>(path)
  }

  deleteMovieInFavourite(movieName: string | null): Observable<boolean>{
    const path: string = this.baseUrl + AppPaths.PATH_DELETE_OF_FAVOURITE
    let checkMovieName: string | null = movieName ?? ''
    let params: HttpParams = new HttpParams().set('movieTitle', checkMovieName)
    return this.http.delete<boolean>(path, {params: params})

  }
}
