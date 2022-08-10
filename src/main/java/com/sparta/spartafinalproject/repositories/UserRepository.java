package com.sparta.spartafinalproject.repositories;

import com.sparta.spartafinalproject.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    User findByName(String name);

    boolean existsByNamen(String name);
}
