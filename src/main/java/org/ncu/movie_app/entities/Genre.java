package org.ncu.movie_app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
//    @JsonIgnoreProperties("genres")
    private List<Movie> movies = new ArrayList<>();
//
//    @JsonProperty("movies") // Custom serialization
//    public List<Map<String, Object>> getMovieList() {
//        return movies.stream()
//                .map(movie -> {
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("movieId", movie.getMovieId());
//                    map.put("movieName", movie.getMovieName());
//                    return map;
//                })
//                .collect(Collectors.toList());
//    }

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

    // Helper method to add movie
    public void addMovie(Movie movie) {
        movies.add(movie);
        movie.getGenres().add(this);
    }

    // Helper method to remove movie
    public void removeMovie(Movie movie) {
        movies.remove(movie);
        movie.getGenres().remove(this);
    }
}