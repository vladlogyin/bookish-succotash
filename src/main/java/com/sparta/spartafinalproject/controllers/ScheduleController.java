package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.Schedule;
import com.sparta.spartafinalproject.documents.Theater;
import com.sparta.spartafinalproject.repositories.ScheduleRepository;
import com.sparta.spartafinalproject.repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleRepository repo;
    @Autowired
    private TheaterRepository theaterRepo;

    @GetMapping("/schedule")
    public List<Schedule> getAllSchedule(){
        return repo.findAll();
    }
    @GetMapping("/schedule/by-id/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable String id){
        if(repo.existsById(id)){
            return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id).get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/schedules/by-time/{time}")
    public List<Schedule> getScheduleByTime(@PathVariable String time){
        return repo.findAllByTime(time);
    }
    @GetMapping("/schedules/by-theaterid/{theaterId}")
    public ResponseEntity<List<Schedule>> getScheduleByTheaterId(@PathVariable String theaterId){
        var possibleTheater = theaterRepo.findById(theaterId);
        if(possibleTheater.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(repo.findAllByTheaterId(theaterId));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/schedule/add")
    public ResponseEntity<String> addSchedule(@RequestBody Schedule newSchedule){
        if(repo.existsById(newSchedule.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This schedule already exists");
        }
        repo.save(newSchedule);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
    @PutMapping("/schedule/update")
    public Schedule updateSchedule(@RequestBody Schedule updatedSchedule){
        repo.save(updatedSchedule);
        return updatedSchedule;
    }
    @DeleteMapping("/schedule/delete/{id}")
    public void deleteScheduleById(@PathVariable String id){
        Schedule toBeDeleted = repo.findById(id).get();
        repo.delete(toBeDeleted);
    }
}
