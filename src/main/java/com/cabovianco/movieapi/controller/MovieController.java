package com.cabovianco.movieapi.controller;

import com.cabovianco.movieapi.exception.NullParameterException;
import com.cabovianco.movieapi.model.Movie;
import com.cabovianco.movieapi.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getMovies());
    }

    @GetMapping("/movie")
    public ResponseEntity<Movie> getMovieByName(@RequestParam String name) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getMovieByName(name));
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getMovieById(id));
    }

    @GetMapping("/movies/genres")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@RequestParam String genre) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getMoviesByGenre(genre));
    }

    @GetMapping("/movies/directors")
    public ResponseEntity<List<Movie>> getMoviesByDirector(@RequestParam String director) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getMoviesByDirector(director));
    }

    @PostMapping("/movies")
    public ResponseEntity<Object> addMovie(@RequestBody Movie movie) {
        try {
            service.addMovie(movie);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(null);
        } catch (NullParameterException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/movie")
    public ResponseEntity<Void> deleteMovieByName(@RequestParam String name) {
        service.deleteMovieByName(name);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable Long id) {
        service.deleteMovieById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

}
