package com.stackroute.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document
public class Movie {

    @Id
    @NotNull
    @Min(1)
    int id;
    @Size(min=2,message="movie name should have atleast 2 characters")
    @NotNull
    String movieName;
    @Size(min=4,message="director's anme shoudl be atleast 4 characters long")
    @NotNull
    String directedBy;
    @Size(message = "the rating should be less than 10 and not more than 2 decimal places")
    @NotNull
    String rating;
    @NotNull
    String posterURL;
    @NotNull
    @Min(1500)
    int yearOfRelease;

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirectedBy() {
        return directedBy;
    }

    public void setDirectedBy(String directedBy) {
        this.directedBy = directedBy;
    }

    public Movie() {}

    public Movie(int id, String movieName,String url,int year,String rate,String director) {
        this.id = id;
        this.movieName = movieName;
        this.posterURL = url;
        this.yearOfRelease = year;
        this.rating = rate;
        this.directedBy = director;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", directedBy='" + directedBy + '\'' +
                ", rating='" + rating + '\'' +
                ", posterURL='" + posterURL + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                '}';
    }
}