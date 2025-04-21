package org.ncu.movie_app.services;

import org.ncu.movie_app.entities.Genre;
import java.util.List;

public interface GenreService {
    void saveGenre(Genre genre);
    Genre getGenreById(int id);
    List<Genre> getAllGenres();
    void updateGenre(Genre genre);
//    void deleteGenre(int id);
    void addMovieToGenre(int genreId, int movieId);
}