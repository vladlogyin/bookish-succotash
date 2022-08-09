package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.Movie;
import com.sparta.spartafinalproject.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieRepository repo;
    @GetMapping("/movies")
    public List<Movie> getMovies()
    {
        return repo.findAll();
    }
}
