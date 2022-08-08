package com.sparta.spartafinalproject.repositories;

import com.sparta.spartafinalproject.documents.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="comments")
public interface CommentRepository extends MongoRepository<Comment, String> {


}
