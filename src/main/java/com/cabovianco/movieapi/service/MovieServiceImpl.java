package com.cabovianco.movieapi.service;

import com.cabovianco.movieapi.exception.MovieNotFoundException;
import com.cabovianco.movieapi.model.Movie;
import com.cabovianco.movieapi.repository.MovieRepository;
import com.cabovianco.movieapi.repository.entity.MovieEntity;
import com.cabovianco.movieapi.util.EntityModelMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.cabovianco.movieapi.util.EntityModelMapper.toModel;
import static com.cabovianco.movieapi.util.EntityModelMapper.toEntity;

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
                .map(EntityModelMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public Movie getMovieByName(String name) {
        MovieEntity movie = repository.findMovieByName(name)
                .orElseThrow(MovieNotFoundException::new);

        return toModel(movie);
    }

    @Override
    @SneakyThrows
    public Movie getMovieById(Long id) {
        MovieEntity movie = repository.findById(id)
                .orElseThrow(MovieNotFoundException::new);

        return toModel(movie);
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {
        return repository.findMoviesByGenre(genre)
                .stream()
                .map(EntityModelMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getMoviesByDirector(String director) {
        return repository.findMoviesByDirector(director)
                .stream()
                .map(EntityModelMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public Movie addMovie(Movie movie) {
        return toModel(repository.save(toEntity(movie)));
    }

    @Override
    @SneakyThrows
    public Movie updateMovie(Long id, Movie movie) {
        MovieEntity entity = repository.findById(id)
                .orElseThrow(MovieNotFoundException::new);

        MovieEntity updatedEntity = toEntity(movie);
        entity.setName(updatedEntity.getName());
        entity.setGenre(updatedEntity.getGenre());
        entity.setDirector(updatedEntity.getDirector());
        entity.setTimeInMinutes(updatedEntity.getTimeInMinutes());

        return toModel(repository.save(entity));
    }

    @Override
    @SneakyThrows
    public Movie deleteMovieById(Long id) {
        MovieEntity movie = repository.findById(id)
                .orElseThrow(MovieNotFoundException::new);

        repository.delete(movie);
        return toModel(movie);
    }

}
