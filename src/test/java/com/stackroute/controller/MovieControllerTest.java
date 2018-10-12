package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Movie;
import com.stackroute.exception.MovieAlreadyExistsException;
import com.stackroute.exception.MovieNotFoundException;
import com.stackroute.repository.MovieRepository;
import com.stackroute.service.MovieServices;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MovieControllerTest {

    int id;
    @Autowired
    MockMvc mockMvc;
    Movie movie;

    @MockBean
    MovieServices movieServices;

    @MockBean
    MovieRepository movieRepository;

    @InjectMocks
    MovieController movieController;

    List<Movie> list= null;

    @Before
    public void setUp() {
        id=1;
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
        movie = new Movie(id,"saw2","ww.www.wsdsd.cds",2003,"8.9","crazy_guy");
        list = new ArrayList<>();
        id++;
        list.add(movie);
    }

    @After
    public void destroyer() {
        list = null;
    }

    @Test
    public void saveMovie() throws Exception {
        when(movieServices.saveMovie(any())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllMovies() throws Exception {
        when(movieServices.getAllMovies()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/list")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getMovies() throws Exception {
        when(movieServices.getAllMovies()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/list")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void searchMovie() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/"+movie.getMovieName())
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteMovie() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/delete/"+String.valueOf(movie.getId()))
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateMovie() {
        try {
            movieRepository.findById(1).get().setMovieName("dzfcsdfsd");
            movieRepository.save(movieRepository.findById(1).get());
            mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/update/"+String.valueOf(movie.getId()))
                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}