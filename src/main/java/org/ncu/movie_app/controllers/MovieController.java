package org.ncu.movie_app.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.ncu.movie_app.entities.Genre;
import org.ncu.movie_app.entities.Movie;
import org.ncu.movie_app.services.GenreService;
import org.ncu.movie_app.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private GenreService genreService;

    @GetMapping
    public List<Movie> fetchMovies(@RequestParam int page, @RequestParam int size) {
        return movieService.getMoviesPaginated(page, size);
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @PostMapping(value = "/addmoviePS", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addMovie(@RequestBody Map<String, Object> requestMap) {
        try {

            Movie movie = new Movie();
            movie.setMovieName((String) requestMap.get("movieName"));
            movie.setMovieDesc((String) requestMap.get("movieDesc"));
            movie.setMovieRating(Double.parseDouble(requestMap.get("movieRating").toString()));
            movie.setMoviePrice(new BigDecimal(requestMap.get("moviePrice").toString()));

            List<Map<String, Object>> genresList = (List<Map<String, Object>>) requestMap.get("genres");
            if (genresList != null) {
                for (Map<String, Object> genreMap : genresList) {
                    Integer genreId = Integer.parseInt(genreMap.get("genreId").toString());
                    Genre genre = genreService.getGenreById(genreId);
                    if (genre != null) {
                        movie.getGenres().add(genre);
                        genre.getMovies().add(movie);
                    }
                }
            }

            movieService.saveMovie(movie);
            return ResponseEntity.ok("Movie added via special endpoint");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateMovie(@PathVariable int id, @RequestBody Movie movie) {
        movie.setMovieId(id);
        movieService.updateMovieByID(id, movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id) {
        movieService.removeMovieByID(id);
    }

    @PostMapping("/{movieId}/genres/{genreId}")
    public ResponseEntity<String> addGenreToMovie(@PathVariable int movieId, @PathVariable int genreId) {
        genreService.addMovieToGenre(genreId, movieId);
        return ResponseEntity.ok("Genre added to movie successfully");
    }

    @GetMapping("/{id}/genres")
    public List<Genre> getMovieGenres(@PathVariable int id) {
        Movie movie = movieService.getMovieById(id);
        return movie != null ? movie.getGenres() : null;
    }

}
