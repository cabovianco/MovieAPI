package com.cabovianco.movieapi.controller;

import com.cabovianco.movieapi.model.Movie;
import com.cabovianco.movieapi.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        return ResponseEntity.ok(service.getMovies());
    }

    @GetMapping("/name")
    public ResponseEntity<Movie> getMovieByName(@RequestParam String name) {
        return ResponseEntity.ok(service.getMovieByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getMovieById(id));
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@RequestParam String genre) {
        return ResponseEntity.ok(service.getMoviesByGenre(genre));
    }

    @GetMapping("/director")
    public ResponseEntity<List<Movie>> getMoviesByDirector(@RequestParam String director) {
        return ResponseEntity.ok(service.getMoviesByDirector(director));
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@Valid @RequestBody Movie movie) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addMovie(movie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @Valid @RequestBody Movie movie) {
        return ResponseEntity.ok(service.updateMovie(id, movie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteMovieById(id));
    }

}
