package com.stackroute.repository;


import com.stackroute.domain.Movie;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


//This is integrated test we need DB server
@RunWith(SpringRunner.class)
@DataMongoTest

public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;
    Movie movie;

    @Before
    public void setUp() {
        movie = new Movie(1,"saw2","ww.www.wsdsd.cds",2003,"8.9","crazy_guy");

    }

    @Test
    public void testSaveUser(){
        movieRepository.save(movie);
        Movie fetchUser = movieRepository.findById(movie.getId()).get();
        Assert.assertEquals(1,fetchUser.getId());

    }

    @Test
    public void testSaveUserFailure(){
        Movie mov = new Movie(1,"saw2","ww.www.wsdsd.cds",2003,"8.9","crazy_guy");
        movieRepository.save(mov);
        Movie fetchUser = movieRepository.findById(mov.getId()).get();
        Assert.assertNotSame(movie,fetchUser);
    }

    @Test
    public void testGetAllUser(){
        Movie mov = new Movie(1,"saw2","ww.www.wsdsd.cds",2003,"8.9","crazy_guy");
        Movie mov1 = new Movie(2,"saw3","ww.www.wsefsdsd.cds",2006,"9","crazy_guy");
        movieRepository.save(mov);
        movieRepository.save(mov1);

        List<Movie> list = movieRepository.findAll();
        Assert.assertEquals("saw2",list.get(0).getMovieName());
    }

    @Test
    public void testDeleteMovie(){
        movieRepository.save(movie);
        movieRepository.delete(movie);
        Assert.assertEquals(Optional.empty(),movieRepository.findById(movie.getId()));
    }

}
