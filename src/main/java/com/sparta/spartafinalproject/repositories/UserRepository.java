package com.sparta.spartafinalproject.repositories;

import com.sparta.spartafinalproject.documents.Comment;
import com.sparta.spartafinalproject.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
