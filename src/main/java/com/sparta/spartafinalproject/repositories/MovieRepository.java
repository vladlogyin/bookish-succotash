package com.sparta.spartafinalproject.repositories;

import com.sparta.spartafinalproject.documents.Comment;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel="movies")
public interface MovieRepository MongoRepository<Movies, String>{

        }
