package com.stackroute.service;

import com.stackroute.domain.Movie;
import com.stackroute.exception.MovieNotFoundException;
import com.stackroute.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieServicesImpl implements MovieServices {

    @Autowired
    MovieRepository movieRepository;

    public MovieServicesImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        movieRepository.save(movie);
        return movie;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> list = this.movieRepository.findAll();
        return list;
    }

    @Override
    public String getRatingOfMovie(Movie movie) {
        if(movieExists(1,0,movie.getMovieName())) return movie.getRating();
        return "this movie doesn't exist...what do want from me??";
    }

    @Override
    public String getDirectorName(Movie movie) {
        if(movieExists(1,0,movie.getMovieName())) return movie.getDirectedBy();
        return "there is no such movie...stop messing with it";
    }

    @Override
    public Movie getMovieById(int movieId) {
        if(movieExists(0,movieId,"")) return movieRepository.findById(movieId).get();
        System.out.println("please enter a valid movie Id");
        return null;
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
    public boolean compMovies(Movie m1, Movie m2) {
        if(m1.getMovieName().compareTo(m2.getMovieName())==0
                && m1.getPosterURL().compareTo(m2.getPosterURL())==0 && m1.getYearOfRelease()==m2.getYearOfRelease()
                && m1.getRating().compareTo(m2.getRating())==0
                && m1.getDirectedBy().compareTo(m2.getDirectedBy())==0) return true;
        return false;
    }

    @Override
    public String getMovieName(int movieId) {
        if(movieExists(0,movieId,"")) return movieRepository.findById(movieId).get().getMovieName();
        return "enter a valid movie id";
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
        if(movieRepository.existsById(movieId)) {
            movieRepository.delete(movieRepository.findById(movieId).get());
        }
        System.out.println("there is no movie with this ID");
        return;
    }

    @Override
    public void updateMovie(int id,Movie movie) {
        saveMovie(movie);
    }

    @Override
    public List<Movie> findMovieByName(String name) throws MovieNotFoundException {
        if(movieRepository.findBymovieName(name) !=null) {
            List<Movie> search=movieRepository.findBymovieName(name);
            return search;
        }
        else {
            throw new MovieNotFoundException("movie doesn't exist");
        }
    }
}
