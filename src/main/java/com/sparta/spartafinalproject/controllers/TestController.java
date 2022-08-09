package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.Comment;
import com.sparta.spartafinalproject.documents.Movie;
import com.sparta.spartafinalproject.repositories.CommentRepository;
import com.sparta.spartafinalproject.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private CommentRepository repo;

    @Autowired
    private MovieRepository repo2;

    @GetMapping("/comments")
    public List<Comment> getComments()
    {
        return repo.findAll();
    }
    @GetMapping("/comment/by-id")
    public Comment getComment()
    {
        return repo.findById("5a9427648b0beebeb6957a22").get();
    }
    @GetMapping("/commentcount")
    public long getCommentCount()
    {
        return repo.count();
    }

    @GetMapping("/movies")
    public List<Movie> getMovies()
    {
        return repo2.findAll();
    }
}
