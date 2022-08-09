package com.sparta.spartafinalproject.repositories;

import com.sparta.spartafinalproject.documents.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {


}
