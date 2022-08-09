package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.Movie;
import com.sparta.spartafinalproject.documents.Theater;
import com.sparta.spartafinalproject.repositories.MovieRepository;
import com.sparta.spartafinalproject.repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TheaterController {

    @Autowired
    private TheaterRepository repo;

    @GetMapping("/theaters")
    public List<Theater> getAllTheaters(){
        return repo.findAll();
    }

    @GetMapping("/theater/by-id/{id}")
    public Theater getTheaterById(@PathVariable String id){
        return repo.findById(id).get();
    }

    @GetMapping("/theater/by-theaterid/{id}")
    public Theater getTheaterByTheaterId(@PathVariable int id){
        return repo.findByTheaterId(id).get();
    }

    @PostMapping("/theater/add")
    public Theater addTheater(@RequestBody Theater newTheater){
        repo.save(newTheater);
        return newTheater;
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
