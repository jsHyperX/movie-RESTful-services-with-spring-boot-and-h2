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
        if(movieExists(1,0,movie.getMovieName())) return movie.getId();
        return -1;
    }

    @Override
    public Movie getMovieById(int movieId) {
        if(movieExists(0,movieId,"")) return movieRepository.findById(movieId).get();
        System.out.println("please enter a valid movie Id");
        return null;
    }

    @Override
    public int getMoviePrice(int movieId) {
        if(movieExists(0,movieId,"")) return movieRepository.findById(movieId).get().getPrice();
        System.out.println("there is not such movie");
        return -1;
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
    public boolean movieExists(int arg, int id, String name) {
        if(arg==0) {
            return (movieRepository.findById(id)!=null);
        }
        else {
            for(Movie m: getAllMovies()) {
                if(m.getMovieName().compareTo(name)==0) return true;
            }
        }
        return false;
    }

    @Override
    public String getMovieName(int movieId) {
        if(movieExists(0,movieId,"")) return movieRepository.findById(movieId).get().getMovieName();
        return "enter a valid movie id";
    }

    @Override
    public String getMovieGenre(int movieId) {
        if(movieExists(0,movieId,"")) return movieRepository.findById(movieId).get().getMovieGenre();
        return "movie doesn't exist";
    }

    @Override
    public String showMovieList(List<Movie> list) {
        String res="";
        for(Movie m: list)  {
            res+=m.toString()+"\n";
        }
        return res;
    }

    @Override
    public void deleteMovie(int movieId) {
        if(movieExists(0,movieId,"")) movieRepository.delete(movieRepository.findById(movieId).get());
        System.out.println("there is no movie with this ID");
        return;
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
