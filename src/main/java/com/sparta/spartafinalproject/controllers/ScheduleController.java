package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.Schedule;
import com.sparta.spartafinalproject.documents.Theater;
import com.sparta.spartafinalproject.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.spi.ResolveResult;
import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleRepository repo;

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

    @GetMapping("/schedule/by-time/{time}")
    public ResponseEntity<Schedule> getScheduleByTime(@PathVariable String time){
        if(repo.existsByTime(time)){
            return ResponseEntity.status(HttpStatus.OK).body(repo.findByTime(time));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/Schedule/by-theaterid/{theaterId}")
    public ResponseEntity<List<Object>> getScheduleByTheaterId(@PathVariable int theaterId){
        if(repo.existsByTheaterId(theaterId)){
            return ResponseEntity.status(HttpStatus.OK).body(repo.findByTheaterId(theaterId));
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
