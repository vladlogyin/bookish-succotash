package com.sparta.spartafinalproject.repositories;

import com.sparta.spartafinalproject.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends MongoRepository<User, String> {

}
