package com.stackroute.controller;

import com.stackroute.domain.Movie;
import com.stackroute.exception.MovieAlreadyExistsException;
import com.stackroute.exception.MovieNotFoundException;
import com.stackroute.service.MovieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class MovieController {

    MovieServices movieServices;

    @Autowired
    public MovieController(MovieServices movieServices) {
        this.movieServices = movieServices;
    }

    @RequestMapping("add")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
        ResponseEntity responseEntity=null;
        try {
            for(Movie m:movieServices.getAllMovies()) {
                if(movie.getId()==m.getId()) {
                    responseEntity = new ResponseEntity<String>("movie already exists",HttpStatus.NOT_ACCEPTABLE);
                    throw new MovieAlreadyExistsException();
                }
            }
            movieServices.saveMovie(movie);
            responseEntity = new ResponseEntity<String>("movie added successfully",HttpStatus.CREATED);
        }catch(MovieAlreadyExistsException ex) {
            System.out.println(ex.getMessage());
        }catch(Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @RequestMapping("list")
    public ResponseEntity<?> getMovies() {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<Movie>>(this.movieServices.getAllMovies(),HttpStatus.OK);
        }catch(Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @RequestMapping("update/{id}")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie,@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            movieServices.updateMovie(id,movie);
            responseEntity = new ResponseEntity<String>("movie updated successfully",HttpStatus.OK);
        }catch(Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @RequestMapping("delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable int id) {
        ResponseEntity responseEntity=null;
        try {
            boolean found=false;
            for(Movie m:movieServices.getAllMovies()) {
                if(id==m.getId()) {
                    found=true;
                }
            }
            if(!found) {
                responseEntity = new ResponseEntity<String>("movie doesn't exists",HttpStatus.NOT_ACCEPTABLE);
                throw new MovieNotFoundException("movie doesn't exist");
            }
            else movieServices.deleteMovie(id);
            responseEntity = new ResponseEntity<String>("movie deleted successfully",HttpStatus.OK);
        }catch(MovieNotFoundException ex) {
            System.out.println(ex.getStackTrace());
        }catch(Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

}
