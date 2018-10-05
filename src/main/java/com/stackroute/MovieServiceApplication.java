package com.stackroute;

import com.stackroute.domain.Movie;
import com.stackroute.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
public class MovieServiceApplication implements ApplicationListener<ContextRefreshedEvent> {

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class,args);
	}

	private MovieRepository movieRepository;
	@Autowired
	public MovieServiceApplication(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		movieRepository.save(new Movie(1,"a","b",230,(float)8.34,"d"));
		movieRepository.save(new Movie(2,"aa","bb",240,(float)7.45,"dd"));
		movieRepository.save(new Movie(3,"aaa","bbb",250,(float)2.21,"ddd"));
	}
}
