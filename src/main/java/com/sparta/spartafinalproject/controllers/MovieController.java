package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.Movie;
import com.sparta.spartafinalproject.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Movie> getMovieById(@PathVariable String id){
        if(repo.existsById(id)){
            return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id).get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    @GetMapping("/movies/title/{title}")
    public ResponseEntity<List<Movie>> getMovieByTitle(@PathVariable String title){
        if(repo.existsByTitle(title)){
            return ResponseEntity.status(HttpStatus.OK).body(repo.findByTitleContainsIgnoreCase(title));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/movies/add")
    public ResponseEntity<String> addMovie(@RequestBody Movie newMovie){
        if(isMovieValid(newMovie))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
        }
        if(repo.existsById(newMovie.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This movie already exists");
        }
        repo.save(newMovie);
        return ResponseEntity.status(HttpStatus.OK).body("Success");

    }

    @PutMapping("/movies/update")
    public ResponseEntity<String> updateMovie(@RequestBody Movie updatedMovie){
        if(isMovieValid(updatedMovie))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
        }
        repo.save(updatedMovie);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @DeleteMapping("/movies/delete/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable String id){
        if(!repo.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("the schedule was not found");
        }
        Movie toBeDeleted = repo.findById(id).get();
        repo.delete(toBeDeleted);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    private boolean isMovieValid(Movie mov)
    {
        return mov.getTitle().length()>0 && mov.getPlot().length()>0 && mov.getCommentCount()>=0;
    }

}
