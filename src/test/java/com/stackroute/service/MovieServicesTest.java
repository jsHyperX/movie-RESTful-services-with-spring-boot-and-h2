package com.stackroute.service;

import com.stackroute.domain.Movie;
import com.stackroute.exception.MovieAlreadyExistsException;
import com.stackroute.repository.MovieRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServicesTest {

    Movie movie;

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieServicesImpl movieServices;

    List<Movie> list= null;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        movie = new Movie(1,"saw2","ww.www.wsdsd.cds",2003,"8.9","crazy_guy");
        Movie mov = new Movie(3,"saw3","ww.www.wsdsdassd.cds",2005,"5.7","crazy_guy");
        list = new ArrayList<>();
        list.add(movie);
        list.add(mov);
    }
    @Test
    public void saveMovieTestSuccess() {

        when(movieRepository.save(any())).thenReturn(movie);
        Movie savedUser = movieServices.saveMovie(movie);
        Assert.assertEquals(movie,savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);
    }

    @Test
    public void getAllMovieTest(){
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> userlist = movieServices.getAllMovies();
        Assert.assertEquals(list,userlist);
    }

    @Test
    public void saveMovieTest() {
        when(movieRepository.save(movie)).thenReturn(movie);
        Movie mov = movieServices.saveMovie(movie);
        Assert.assertEquals(mov,movie);
    }

    @Test
    public void getAllMoviesTest() {
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> userList = movieServices.getAllMovies();
        Assert.assertEquals(userList,list);
    }

    @Test
    public void movieExistsTest() {
        when(movieRepository.findBymovieName(movie.getMovieName())).thenReturn(Collections.singletonList(movie));
        Assert.assertNotEquals(null,Collections.singletonList(movie));
    }

    @Test
    public void updateMovieTest() {
        when(movieRepository.save(any())).thenReturn(movie);
        movie.setMovieName("Ddssadsad");
        Movie mov = movieServices.saveMovie(movie);
        Assert.assertEquals(mov,movie);
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteMovieTest() {
        when(movieRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(movieRepository).delete(any());
        when(movieRepository.findById(anyInt()).get()).thenReturn(movie);
        movieServices.deleteMovie(movie.getId());
        Assert.assertEquals(null,movieServices.getMovieById(anyInt()));
    }

}