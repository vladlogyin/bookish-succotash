package com.sparta.spartafinalproject.repositories;

import com.sparta.spartafinalproject.documents.Theater;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheaterRepository extends MongoRepository<Theater,String> {
    Optional<Theater> findByTheaterId(int id);
    void deleteByTheaterId(int id);

    boolean existByTheaterId(int id);
}
