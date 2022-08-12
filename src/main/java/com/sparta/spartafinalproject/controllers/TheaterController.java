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
    public ResponseEntity<Theater> getTheaterByTheaterId(@PathVariable int id){
        var possibleTheater = repo.findByTheaterId(id);
        if(!possibleTheater.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(possibleTheater.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/theater/add")
    public ResponseEntity<String> addTheater(@RequestBody Theater newTheater){
        var possibleTheater = repo.findById(newTheater.getId());
        if(possibleTheater.isPresent()||!isValidTheater(newTheater)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ewwow"); //uwu
        }
        repo.save(newTheater);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PutMapping("/theater/update")
    public ResponseEntity<String> updateTheater(@RequestBody Theater updatedTheater){
        if(!isValidTheater(updatedTheater)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This theater already exists");
        }
        repo.save(updatedTheater);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @DeleteMapping("/theater/delete/{id}")
    public void deleteTheaterById(@PathVariable String id){
        Theater toBeDeleted = repo.findById(id).get();
        repo.delete(toBeDeleted);
    }

    private boolean isValidTheater(Theater th)
    {
        var possibleDuplicate = repo.findByTheaterId(th.getTheaterId());
        return !(possibleDuplicate.isPresent()&&possibleDuplicate.get().getId()!=th.getId());
    }

}
