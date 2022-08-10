package com.sparta.spartafinalproject.repositories;

import com.sparta.spartafinalproject.documents.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    List<Schedule> findAllByTheaterId(int id);

    List<Schedule> findAllByMovieId(String movieId);

    List<Schedule> findAllByTime(String time);
}
