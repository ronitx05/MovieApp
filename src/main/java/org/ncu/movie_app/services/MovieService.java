package org.ncu.movie_app.services;

import org.ncu.movie_app.entities.Movie;
import java.util.List;

public interface MovieService {
    void saveMovie(Movie movie);
    Movie getMovieById(int id);
    List<Movie> getMoviesPaginated(int page, int size);
    void updateMovieByID(int id, Movie movie);
    void removeMovieByID(int id);
}
