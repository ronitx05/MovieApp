package org.ncu.movie_app.controllers;

import java.util.List;
import org.ncu.movie_app.entities.Movie;
import org.ncu.movie_app.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> fetchMovies(@RequestParam int page, @RequestParam int size) {
        return movieService.getMoviesPaginated(page, size);
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @PostMapping("/addmoviePS")
    public ResponseEntity<String> addMovieSpecial(@RequestBody Movie movie) {
        movieService.saveMovie(movie);
        return ResponseEntity.ok("Movie added via special endpoint");
    }

    @PutMapping("/{id}")
    public void updateMovie(@PathVariable int id, @RequestBody Movie movie) {
        movie.setMovieId(id);
        movieService.updateMovieByID(id, movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id) {
        movieService.removeMovieByID(id);
    }
}
