package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.Schedule;
import com.sparta.spartafinalproject.documents.Theater;
import com.sparta.spartafinalproject.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Schedule getScheduleById(@PathVariable String id){

        return repo.findById(id).get();
    }
    @GetMapping("/Schedule/by-theaterid/{id}")
    public List<Object> getScheduleByTheaterId(@PathVariable int id){

        return repo.findByTheaterId(id);
    }
    @PostMapping("/schedule/add")
    public Schedule addTheater(@RequestBody Schedule newSchedule){
        repo.save(newSchedule);
        return newSchedule;
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
