package com.cabovianco.movieapi.service;

import com.cabovianco.movieapi.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getMovies();

    Movie getMovieByName(String name);

    Movie getMovieById(Long id);

    List<Movie> getMoviesByGenre(String genre);

    List<Movie> getMoviesByDirector(String director);

    Movie addMovie(Movie movie);

    Movie updateMovie(Long id, Movie movie);

    Movie deleteMovieById(Long id);

}
