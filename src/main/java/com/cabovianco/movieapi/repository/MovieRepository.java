package com.cabovianco.movieapi.repository;

import com.cabovianco.movieapi.repository.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    @Query(value = "SELECT * FROM movies ORDER BY id", nativeQuery = true)
    List<MovieEntity> findAllMovies();

    @Query(value = "SELECT * FROM movies WHERE name = :name LIMIT 1", nativeQuery = true)
    Optional<MovieEntity> findMovieByName(String name);

    @Query(value = "SELECT * FROM movies WHERE genre = :genre ORDER BY id", nativeQuery = true)
    List<MovieEntity> findMoviesByGenre(String genre);

    @Query(value = "SELECT * FROM movies WHERE director = :director ORDER BY id", nativeQuery = true)
    List<MovieEntity> findMoviesByDirector(String director);

}
