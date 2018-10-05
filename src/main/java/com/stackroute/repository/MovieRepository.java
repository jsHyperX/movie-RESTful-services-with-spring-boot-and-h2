package com.stackroute.repository;

import com.stackroute.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
    List<Movie> findBymovieName(String movieName);
}
