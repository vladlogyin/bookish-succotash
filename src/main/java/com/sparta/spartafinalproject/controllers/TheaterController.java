package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.Theater;
import com.sparta.spartafinalproject.repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TheaterController {

    @Autowired
    private TheaterRepository repo;

    @GetMapping("/theaters")
    public List<Theater> getAllTheaters(){
        return repo.findAll();
    }

    @GetMapping("/theater/by-id/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable String id){
        if(repo.existsById(id)){
            return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id).get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/theater/by-theaterid/{id}")
    public ResponseEntity<Optional<Theater>> getTheaterByTheaterId(@PathVariable int id){
        if(repo.existByTheaterId(id)){
            return ResponseEntity.status(HttpStatus.OK).body(repo.findByTheaterId(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/theater/add")
    public ResponseEntity<String> addTheater(@RequestBody Theater newTheater){
        if(repo.existsById(newTheater.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This theater already exists");
        }
        repo.save(newTheater);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PutMapping("/theater/update")
    public Theater updateTheater(@RequestBody Theater updatedTheater){
        repo.save(updatedTheater);
        return updatedTheater;
    }

    @DeleteMapping("/theater/delete/{id}")
    public void deleteTheaterById(@PathVariable String id){
        Theater toBeDeleted = repo.findById(id).get();
        repo.delete(toBeDeleted);
    }

}
