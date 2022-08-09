package com.sparta.spartafinalproject.repositories;

import com.sparta.spartafinalproject.documents.Theater;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends MongoRepository<Theater,String> {

}
