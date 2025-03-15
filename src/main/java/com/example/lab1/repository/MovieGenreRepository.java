package com.example.lab1.repository;

import com.example.lab1.model.Genre;
import com.example.lab1.model.Movie;
import com.example.lab1.model.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {
    @Query("SELECT mg.genre FROM MovieGenre mg WHERE mg.movie.movieId = :movieId")
    List<Genre> findGenresByMovieId(@Param("movieId") Long movieId);
}
