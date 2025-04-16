package org.ncu.movie_app.controllers;

import org.ncu.movie_app.entities.Genre;
import org.ncu.movie_app.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping
    public ResponseEntity<String> createGenre(@RequestBody Genre genre) {
        genreService.saveGenre(genre);
        return ResponseEntity.ok("Genre created successfully");
    }

    @GetMapping("/{id}")
    public Genre getGenre(@PathVariable int id) {
        return genreService.getGenreById(id);
    }

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGenre(@PathVariable int id, @RequestBody Genre genre) {
        genre.setGenreId(id);
        genreService.updateGenre(genre);
        return ResponseEntity.ok("Genre updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable int id) {
        genreService.deleteGenre(id);
        return ResponseEntity.ok("Genre deleted successfully");
    }

    @PostMapping("/{genreId}/movies/{movieId}")
    public ResponseEntity<String> addMovieToGenre(@PathVariable int genreId, @PathVariable int movieId) {
        genreService.addMovieToGenre(genreId, movieId);
        return ResponseEntity.ok("Movie added to genre successfully");
    }
}