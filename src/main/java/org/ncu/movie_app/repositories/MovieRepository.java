package org.ncu.movie_app.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.ncu.movie_app.entities.Genre;
import org.ncu.movie_app.entities.Movie;
import org.springframework.stereotype.Repository;
import java.util.HashSet;
import java.util.List;

@Repository
@Transactional
public class MovieRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveMovie(Movie movie) {
        if (movie.getMovieId() == 0) {
            entityManager.persist(movie);
        } else {
            entityManager.merge(movie);
        }
    }

    public Movie getMovieById(int id) {
        return entityManager.find(Movie.class, id);
    }

    public List<Movie> getMoviesPaginated(int page, int size) {
        TypedQuery<Movie> query = entityManager.createQuery("SELECT m FROM Movie m", Movie.class);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Transactional
    public void updateMovie(Movie movie) {
        entityManager.merge(movie);
    }

    @Transactional
    public void deleteMovieById(int id) {
        Movie movie = entityManager.find(Movie.class, id);
        if (movie != null) {

            for (Genre genre : new HashSet<>(movie.getGenres())) {
                genre.getMovies().remove(movie);
            }
            movie.getGenres().clear();

            entityManager.remove(movie);
        }
    }
}
