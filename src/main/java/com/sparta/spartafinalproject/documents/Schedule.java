package com.sparta.spartafinalproject.documents;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("schedule")
public class Schedule {

    @Id
    private String id;

    @Field(name="movie")
    private String movieId;
    @Field(name="theaterId")
    private String theaterId;
    private String time;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movie) {
        this.movieId = movie;
    }

    public String getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(String theater) {
        this.theaterId = theater;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
