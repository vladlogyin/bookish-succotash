package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.Comment;
import com.sparta.spartafinalproject.documents.Movie;
import com.sparta.spartafinalproject.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Comment> getCommentById(@PathVariable String id){
        if(repo.existsById(id)){
            return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id).get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/comments/name/{name}")
    public ResponseEntity<List<Comment>> getCommentByName(@PathVariable String name){
        if(repo.existsByName(name)){
            return ResponseEntity.status(HttpStatus.OK).body(repo.findAllByName(name));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/comments/email/{email}")
    public ResponseEntity<List<Comment>> getCommentByEmail(@PathVariable String email){
        if(repo.existsByEmail(email)){
            return ResponseEntity.status(HttpStatus.OK).body(repo.findAllByEmail(email));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/comments/movie")
    public ResponseEntity<List<Comment>> getCommentsByMovie(@RequestBody Movie movie){
        if(repo.existsByMovie(movie)){
            return ResponseEntity.status(HttpStatus.OK).body(repo.findAllByMovie(movie));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
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
