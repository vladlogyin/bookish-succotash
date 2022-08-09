package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.Comment;
import com.sparta.spartafinalproject.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository repo;

    @GetMapping("/comments")
    public List<Comment> getAllComments(){
        return repo.findAll();
    }

    @GetMapping("/comments/id/{id}")
    public Comment getCommentById(@PathVariable String id){
        return repo.findById(id).get();
    }

    @GetMapping("/comments/name/{name}")
    public List<Comment> getCommentByName(@PathVariable String name){
        return repo.findByName(name);
    }

    @GetMapping("/comments/email/{email}")
    public List<Comment> getCommentByEmail(@PathVariable String email){
        return repo.findByEmail(email);
    }

    @GetMapping("/comments/movie/{movie}")
    public List<Comment> getCommentByMovie(@PathVariable String movie){
        return repo.findByMovie(movie);
    }

    @PostMapping("/comments/add")
    public Comment addComment(@RequestBody Comment newComment){
        repo.save(newComment);
        return newComment;
    }

    @PutMapping("/comments/update")
    public Comment updateComment(@RequestBody Comment updatedComment){
        repo.save(updatedComment);
        return updatedComment;
    }

    @DeleteMapping("/movcomments/delete/{id}")
    public void deleteCommentById(@PathVariable String id){
        Comment toBeDeleted = repo.findById(id).get();
        repo.delete(toBeDeleted);
    }

}
