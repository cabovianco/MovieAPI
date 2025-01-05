package com.cabovianco.movieapi.service;

import com.cabovianco.movieapi.exception.MovieNotFoundException;
import com.cabovianco.movieapi.exception.NullParameterException;
import com.cabovianco.movieapi.model.Movie;
import com.cabovianco.movieapi.repository.MovieRepository;
import com.cabovianco.movieapi.repository.entity.MovieEntity;
import com.cabovianco.movieapi.util.EntityModelMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.cabovianco.movieapi.util.EntityModelMapper.entityToModel;
import static com.cabovianco.movieapi.util.EntityModelMapper.modelToEntity;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;

    @Autowired
    public MovieServiceImpl(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Movie> getMovies() {
        return repository.findAllMovies()
                .stream()
                .map(EntityModelMapper::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Movie getMovieByName(String name) {
        Optional<MovieEntity> movie = repository.findMovieByName(name);
        if (movie.isEmpty())
            throw new MovieNotFoundException();

        return entityToModel(movie.get());
    }

    @Override
    public Movie getMovieById(Long id) {
        Optional<MovieEntity> movie = repository.findMovieById(id);
        if (movie.isEmpty())
            throw new MovieNotFoundException();

        return entityToModel(movie.get());
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {
        return repository.findMoviesByGenre(genre)
                .stream()
                .map(EntityModelMapper::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getMoviesByDirector(String director) {
        return repository.findMoviesByDirector(director)
                .stream()
                .map(EntityModelMapper::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addMovie(Movie movie) throws NullParameterException {
        if (movie.getName() == null || movie.getGenre() == null ||
                movie.getDirector() == null || movie.getTimeInMinutes() == null)
            throw new NullParameterException();

        MovieEntity entity = modelToEntity(movie);
        repository.addMovie(entity.getName(), entity.getGenre(), entity.getDirector(), entity.getTimeInMinutes());
    }

    @Override
    @Transactional
    public void deleteMovieByName(String name) {
        if (repository.findMovieByName(name).isEmpty())
            throw new MovieNotFoundException();

        repository.deleteMovieByName(name);
    }

    @Override
    @Transactional
    public void deleteMovieById(Long id) {
        if (repository.findMovieById(id).isEmpty())
            throw new MovieNotFoundException();

        repository.deleteMovieById(id);
    }

}
