package com.sparta.spartafinalproject.documents;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import java.util.Date;

@Document(collection="movies")
public class Movie {

    @Id
    @JsonProperty("movie_id")
    private String id;

    @JsonProperty("movie_name")
    private String movieName;

    @JsonProperty("movie_length")
    private Integer length;

    @JsonProperty("movie_time")
    private Integer time;

    public Movie(String id, String movieName, Integer length, Integer time)
    {
        this.id = id;
        this.movieName = movieName;
        this.length = length;
        this.time = time;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }


}
