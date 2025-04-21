package org.ncu.movie_app.services;

import org.ncu.movie_app.entities.Genre;
import org.ncu.movie_app.entities.Movie;
import org.ncu.movie_app.repositories.GenreRepository;
import org.ncu.movie_app.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    @Transactional
    public void saveGenre(Genre genre) {
        genreRepository.saveGenre(genre);
    }

    @Override
    public Genre getGenreById(int id) {
        return genreRepository.getGenreById(id);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.getAllGenres();
    }

    @Override
    @Transactional
    public void updateGenre(Genre genre) {
        genreRepository.updateGenre(genre);
    }

//    @Override
//    @Transactional
//    public void deleteGenre(int id) {
//        genreRepository.deleteGenre(id);
//    }

    @Override
    @Transactional
    public void addMovieToGenre(int genreId, int movieId) {
        Genre genre = genreRepository.getGenreById(genreId);
        Movie movie = movieRepository.getMovieById(movieId);

        if (genre != null && movie != null) {
            genre.addMovie(movie);
            genreRepository.updateGenre(genre);
        }
    }
}