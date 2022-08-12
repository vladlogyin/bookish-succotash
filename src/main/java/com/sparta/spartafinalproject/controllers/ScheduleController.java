package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.Schedule;
import com.sparta.spartafinalproject.documents.Theater;
import com.sparta.spartafinalproject.repositories.MovieRepository;
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

    @Autowired
    private MovieRepository movieRepo;

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
    public ResponseEntity<List<Schedule>> getScheduleByTime(@PathVariable String time){
        if(repo.existsByTime(time)){
            return ResponseEntity.status(HttpStatus.OK).body(repo.findAllByTime(time));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
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
        if(!isScheduleValid(newSchedule)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("uwu something went wong");
        }
        repo.save(newSchedule);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PutMapping("/schedule/update")
    public ResponseEntity<String> updateSchedule(@RequestBody Schedule updatedSchedule){
        if(!isScheduleValid(updatedSchedule)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error something went wrong");
        }
        repo.save(updatedSchedule);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
    @DeleteMapping("/schedule/delete/{id}")
    public ResponseEntity<String> deleteScheduleById(@PathVariable String id){
        if(!repo.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("the schedule was not found");
        }
        Schedule toBeDeleted = repo.findById(id).get();
        repo.delete(toBeDeleted);
        return ResponseEntity.status(HttpStatus.OK).body("Schedule was deleted");
    }

    private boolean isScheduleValid(Schedule sch)
    {
        var possibleMovie = movieRepo.findById(sch.getMovieId());
        var possibleTheater = theaterRepo.findById(sch.getTheaterId());
        //time format HH:mm 24 hour format
        if(sch.getTime()==null)
        {
            return false;
        }
        String timeString = sch.getTime().trim();

        if(timeString.length()!=5&&timeString.charAt(2)!=':')
        {
            return false; // string too long/short or not separated by ':'
        }
        try{
            int hours = Integer.parseInt(timeString.substring(0,2));
            
            int minutes = Integer.parseInt(timeString.substring(3,5));

            return (hours>=0&&hours<24)&&(minutes>=0&&minutes<60)&& possibleMovie.isPresent() && possibleTheater.isPresent();

        }
        catch(Exception e)
        {
            return false; // error parsing time -> invalid schedule
        }
    }
}
