package com.sparta.spartafinalproject.repositories;

import com.sparta.spartafinalproject.documents.Comment;
import com.sparta.spartafinalproject.documents.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByName(String name);

    List<Comment> findAllByEmail(String email);

    List<Comment> findAllByMovie(Movie movie);

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    boolean existsByMovie(Movie movie);
}
