export class AppPaths{


  public static API_URL: string = 'http://localhost:8080'
  public static PATH_MOVIES: string = "/movies"
  public static BASE_URL: string = this.API_URL + this.PATH_MOVIES
  public static PATH_LIST_MOVIE: string = "/list-movies"
  public static PATH_SAVE_MOVIE: string = "/movie-save"
  public static PATH_FAVOURITE_MOVIE_LIST: string = "/favourite-movie-list"
  public static PATH_FAVOURITE_MOVIE_BY_NAME: string = "/favourite-movie-by-name"
  public static PATH_DELETE_OF_FAVOURITE: string = "/delete-favourite"
}
