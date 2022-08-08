package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.Comment;
import com.sparta.spartafinalproject.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private CommentRepository repo;

    @GetMapping("/comments")
    public List<Comment> getComments()
    {
        return repo.findAll();
    }
    @GetMapping("/commentcount")
    public long getCommentCount()
    {
        return repo.count();
    }
}
