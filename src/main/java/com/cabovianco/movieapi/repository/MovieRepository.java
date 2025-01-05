package com.cabovianco.movieapi.repository;

import com.cabovianco.movieapi.repository.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query(value = "SELECT * FROM movies WHERE id = :id", nativeQuery = true)
    Optional<MovieEntity> findMovieById(Long id);

    @Query(value = "SELECT * FROM movies WHERE genre = :genre ORDER BY id", nativeQuery = true)
    List<MovieEntity> findMoviesByGenre(String genre);

    @Query(value = "SELECT * FROM movies WHERE director = :director ORDER BY id", nativeQuery = true)
    List<MovieEntity> findMoviesByDirector(String director);

    @Modifying
    @Query(value = "INSERT INTO movies (name, genre, director, time_in_minutes) VALUES (:name, :genre, :director, :timeInMinutes)", nativeQuery = true)
    void addMovie(String name, String genre, String director, Double timeInMinutes);

    @Modifying
    @Query(value = "DELETE FROM movies WHERE name = :name", nativeQuery = true)
    void deleteMovieByName(String name);

    @Modifying
    @Query(value = "DELETE FROM movies WHERE id = :id", nativeQuery = true)
    void deleteMovieById(Long id);

}
