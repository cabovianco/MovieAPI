package com.cabovianco.movieapi.util;

import com.cabovianco.movieapi.model.Movie;
import com.cabovianco.movieapi.repository.entity.MovieEntity;

public class EntityModelMapper {

    public static MovieEntity modelToEntity(Movie movie) {
        MovieEntity entity = new MovieEntity();
        entity.setId(movie.getId());
        entity.setName(movie.getName());
        entity.setGenre(movie.getGenre());
        entity.setDirector(movie.getDirector());
        entity.setTimeInMinutes(movie.getTimeInMinutes());

        return entity;
    }

    public static Movie entityToModel(MovieEntity movie) {
        Movie model = new Movie();
        model.setId(movie.getId());
        model.setName(movie.getName());
        model.setGenre(movie.getGenre());
        model.setDirector(movie.getDirector());
        model.setTimeInMinutes(movie.getTimeInMinutes());

        return model;
    }

}
