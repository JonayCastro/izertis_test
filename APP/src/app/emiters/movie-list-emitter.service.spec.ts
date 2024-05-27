import { TestBed } from '@angular/core/testing';

import { MovieListEmitterService } from './movie-list-emitter.service';

describe('MovieListEmitterService', () => {
  let service: MovieListEmitterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MovieListEmitterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
