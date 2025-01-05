package com.cabovianco.movieapi.service;

import com.cabovianco.movieapi.exception.NullParameterException;
import com.cabovianco.movieapi.model.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> getMovies();

    Movie getMovieByName(String name);

    Movie getMovieById(Long id);

    List<Movie> getMoviesByGenre(String genre);

    List<Movie> getMoviesByDirector(String director);

    void addMovie(Movie movie) throws NullParameterException;

    void deleteMovieByName(String name);

    void deleteMovieById(Long id);

}
