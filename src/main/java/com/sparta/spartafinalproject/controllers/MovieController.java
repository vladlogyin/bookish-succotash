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
            return ResponseEntity.status(HttpStatus.OK).body(repo.findByTitle(title));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/movies/add")
    public ResponseEntity<String> addMovie(@RequestBody Movie newMovie){
        if(repo.existsById(newMovie.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This movie allready exists");
        }
        repo.save(newMovie);
        return ResponseEntity.status(HttpStatus.OK).body("Success");

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
