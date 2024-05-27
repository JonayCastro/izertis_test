export class MovieVO{

  public title: string | null = null
  public overview: string | null = null
  public isAdultContent: string | null = null

  constructor(model: MovieVO) {
    this.initializer(model)
  }

  initializer(model: MovieVO | null){
    if(model != null){
      this.title = model.title
      this.overview = model.overview
      this.isAdultContent = model.isAdultContent
    }
    return this
  }
}
