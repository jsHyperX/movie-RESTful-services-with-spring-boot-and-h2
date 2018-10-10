package com.stackroute.repository;

import com.stackroute.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie,Integer> {
    List<Movie> findBymovieName(String movieName);
}
