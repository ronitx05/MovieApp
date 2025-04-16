package org.ncu.movie_app.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.ncu.movie_app.entities.Genre;
import org.ncu.movie_app.entities.Movie;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Transactional
public class GenreRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveGenre(Genre genre) {
        entityManager.persist(genre);
    }

    public Genre getGenreById(int id) {
        return entityManager.find(Genre.class, id);
    }

    public List<Genre> getAllGenres() {
        return entityManager.createQuery("SELECT g FROM Genre g", Genre.class).getResultList();
    }

    public void updateGenre(Genre genre) {
        entityManager.merge(genre);
    }

    public void deleteGenre(int id) {
        Genre genre = entityManager.find(Genre.class, id);
        if (genre != null) {
            // Remove associations first
            for (Movie movie : genre.getMovies()) {
                movie.getGenres().remove(genre);
            }
            entityManager.remove(genre);
        }
    }
}