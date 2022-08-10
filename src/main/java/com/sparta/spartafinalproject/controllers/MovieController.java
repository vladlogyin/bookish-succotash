package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.Movie;
import com.sparta.spartafinalproject.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository repo;

    @GetMapping("/movies")
    public List<Movie> getAllMovies(){
        return repo.findAll();
    }

    @GetMapping("/movies/id/{id}")
    public Movie getMovieById(@PathVariable String id){
        return repo.findById(id).get();
    }

    @GetMapping("/movies/title/{title}")
    public List<Movie> getMovieByTitle(@PathVariable String title){
        return repo.findByTitleContainsIgnoreCase(title);
    }

    @PostMapping("/movies/add")
    public Movie addMovie(@RequestBody Movie newMovie){
        repo.save(newMovie);
        return newMovie;
    }

    @PutMapping("/movies/update")
    public Movie updateMovie(@RequestBody Movie updatedMovie){
        repo.save(updatedMovie);
        return updatedMovie;
    }

    @DeleteMapping("/movies/delete/{id}")
    public void deleteMovieById(@PathVariable String id){
        Movie toBeDeleted = repo.findById(id).get();
        repo.delete(toBeDeleted);
    }

}
