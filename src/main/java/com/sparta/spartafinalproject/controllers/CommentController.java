package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.Comment;
import com.sparta.spartafinalproject.documents.Movie;
import com.sparta.spartafinalproject.repositories.CommentRepository;
import com.sparta.spartafinalproject.repositories.MovieRepository;
import com.sparta.spartafinalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository repo;
    @Autowired
    private MovieRepository movieRepo;

    @Autowired
    private UserRepository userRepo;

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
    /* TODO determine if these are relevant fields
    @GetMapping("/comments/name/{name}")
    public ResponseEntity<List<Comment>> getCommentByName(@PathVariable String name){
        if(repo.existsByName(name)){
            return ResponseEntity.status(HttpStatus.OK).body(repo.findAllByName(name));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/comments/email/{email}")
    public ResponseEntity<List<Comment>> getCommentByEmail(@PathVariable String email){

        if(repo.existsByEmail(email)){ // TODO check if email exists -> NOT_FOUND,. If a user hasn't posted any comments, the response should be OK
            return ResponseEntity.status(HttpStatus.OK).body(repo.findAllByEmail(email));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/comments/movie/{id}")
    public ResponseEntity<List<Comment>> getCommentsByMovieId(@PathVariable String id){
        var movie = movieRepo.findById(id);
        if(movie.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(repo.findAllByMovieId(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }*/

    @PostMapping("/comments/add")
    public ResponseEntity<String> addComment(@RequestBody Comment newComment){
        if(repo.existsById(newComment.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This movie allready exists");
        }
        repo.save(newComment);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PutMapping("/comments/update")
    public Comment updateComment(@RequestBody Comment updatedComment){
        repo.save(updatedComment);
        return updatedComment;
    }

    @DeleteMapping("/comments/delete/{id}")
    public void deleteCommentById(@PathVariable String id){
        Comment toBeDeleted = repo.findById(id).get();
        repo.delete(toBeDeleted);
    }

}
