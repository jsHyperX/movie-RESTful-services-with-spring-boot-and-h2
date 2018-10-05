package com.stackroute.service;

import com.stackroute.domain.Movie;

import java.util.List;

public interface MovieServices {

    void saveMovie(Movie movie);
    List<Movie> getAllMovies();
    int getMovieId(Movie movie);
    Movie getMovieById(int movieId);
    int getMoviePrice(int movieId);
    List<Movie> findMovieByName(String name);
    boolean movieExists(int arg,int id,String name);
    String getMovieName(int movieId);
    String getMovieGenre(int movieId);
    String showMovieList(List<Movie> list);
    void deleteMovie(int movieId);
    void updateMovie(int id,Movie movie);

}
