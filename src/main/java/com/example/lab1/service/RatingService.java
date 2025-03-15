package com.example.lab1.service;

import com.example.lab1.model.Rating;
import com.example.lab1.model.Movie;
import com.example.lab1.model.User;
import com.example.lab1.repository.MovieRepository;
import com.example.lab1.repository.RatingRepository;
import com.example.lab1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository, UserRepository userRepository, MovieRepository movieRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    public Rating createRating(Rating rating, String username, String movieTitle) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with username: " + username));

        Movie movie = movieRepository.findMovieByTitle(movieTitle)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found with title: " + movieTitle));

        rating.setUser(user);
        rating.setMovie(movie);

        return ratingRepository.save(rating);
    }

    public List<Rating> findRatingsByMovieId(Long movieId) {
        return ratingRepository.findRatingsByMovie_MovieId(movieId);
    }
}
