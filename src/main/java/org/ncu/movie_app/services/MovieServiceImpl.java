package org.ncu.movie_app.services;

import org.ncu.movie_app.entities.Movie;
import org.ncu.movie_app.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    @Transactional
    public void saveMovie(Movie movie) {
        movieRepository.saveMovie(movie);
    }

    @Override
    public Movie getMovieById(int id) {
        return movieRepository.getMovieById(id);
    }

    @Override
    public List<Movie> getMoviesPaginated(int page, int size) {
        return movieRepository.getMoviesPaginated(page, size);
    }

    @Override
    @Transactional
    public void updateMovieByID(int id, Movie movie) {
        Movie existingMovie = movieRepository.getMovieById(id);
        if (existingMovie != null) {
            existingMovie.setMovieName(movie.getMovieName());
            existingMovie.setMovieDesc(movie.getMovieDesc());
            existingMovie.setMovieRating(movie.getMovieRating());
            existingMovie.setMoviePrice(movie.getMoviePrice());
            movieRepository.updateMovie(existingMovie); // Merges changes
        }
    }

    @Override
    @Transactional
    public void removeMovieByID(int id) {
        movieRepository.deleteMovieById(id);
    }

}
