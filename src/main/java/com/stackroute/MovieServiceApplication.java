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

	MovieRepository movieRepository;

	@Autowired
	public MovieServiceApplication(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		movieRepository.save(new Movie(1,"saw1","adawdxasdawd",2017,"8.3","ALBAL1"));
		movieRepository.save(new Movie(2,"saw2","dasdqwdaDSQQAWD",2018,"7.4","albal2"));
		movieRepository.save(new Movie(3,"saw3","bDASDAdasdaweS",2018,"2.2","albal3"));
	}
}
