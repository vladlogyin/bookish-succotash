package com.sparta.spartafinalproject.repositories;

import com.sparta.spartafinalproject.documents.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    List<Object> findByTheaterId(int theaterId);

    Schedule findByTime(String time);

    boolean existsByTime(String time);

    boolean existsByTheaterId(int theaterId);
}
