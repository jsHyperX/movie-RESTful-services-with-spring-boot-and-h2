package com.stackroute.service;

import com.stackroute.domain.Movie;

import java.util.List;

public interface MovieServices {

    void saveMovie(Movie movie);
    List<Movie> getAllMovies();
    String getRatingOfMovie(Movie movie);
    String getDirectorName(Movie movie);
    Movie getMovieById(int movieId);
    boolean movieExists(int arg,int id,String name);
    boolean compMovies(Movie m1,Movie m2);
    String getMovieName(int movieId);
    String showMovieList(List<Movie> list);
    void deleteMovie(int movieId);
    void updateMovie(int id,Movie movie);

}
