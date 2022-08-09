package com.sparta.spartafinalproject.repositories;

import com.sparta.spartafinalproject.documents.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    List<Movie> findAllByTitleContainsIgnoreCase(String s);
}
