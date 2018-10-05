package com.stackroute.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Movie {

    @Id
    int id;
    String movieName;
    String directedBy;
    String rating;
    String posterURL;
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