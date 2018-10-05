package com.stackroute.service;

import com.stackroute.domain.Movie;
import com.stackroute.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServicesImpl implements MovieServices {

    MovieRepository movieRepository;

    @Autowired
    public MovieServicesImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> list = (List<Movie>) movieRepository.findAll();
        return list;
    }

    @Override
    public int getMovieId(Movie movie) {
        return movie.getId();
    }

    @Override
    public Movie getMovieById(int movieId) {
        return movieRepository.findById(movieId).get();
    }

    @Override
    public int getMoviePrice(int movieId) {
        return movieRepository.findById(movieId).get().getPrice();
    }

    @Override
    public List<Movie> findMovieByName(String name) {
        List<Movie> movies = new ArrayList<>();
        for(Movie m: getAllMovies()) {
            if(m.getMovieName().compareTo(name)==0) {
                movies.add(m);
            }
        }
        return movies;
    }

    @Override
    public String getMovieName(int movieId) {
        return movieRepository.findById(movieId).get().getMovieName();
    }

    @Override
    public String getMovieGenre(int movieId) {
        return movieRepository.findById(movieId).get().getMovieGenre();
    }

    @Override
    public void deleteMovie(int movieId) {
        movieRepository.delete(movieRepository.findById(movieId).get());
    }

    @Override
    public void updateMovie(int id,Movie movie) {
        Movie mov = getMovieById(id);
        mov.setMovieName(movie.getMovieName());
        mov.setMovieGenre(movie.getMovieGenre());
        mov.setPrice(movie.getPrice());
        saveMovie(mov);
    }
}
