package org.ncu.movie_app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private int genreId;

    @Column(name = "genre_name", nullable = false, unique = true)
    private String genreName;

    @ManyToMany(mappedBy = "genres", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference("movie-genre")
    private List<Movie> movies = new ArrayList<>();

    // Constructors, getters, setters
    public Genre() {}

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    // Getters and setters
    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        movie.getGenres().add(this);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
        movie.getGenres().remove(this);
    }
}