package com.sparta.spartafinalproject.controllers;

import com.mongodb.assertions.Assertions;
import com.sparta.spartafinalproject.documents.Comment;
import com.sparta.spartafinalproject.documents.Movie;
import com.sparta.spartafinalproject.repositories.CommentRepository;
import com.sparta.spartafinalproject.repositories.MovieRepository;
import com.sparta.spartafinalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping("/comments/name/{name}")
    public ResponseEntity<List<Comment>> getCommentByName(@PathVariable String name) {
        if (repo.existsByName(name)) {
            return ResponseEntity.status(HttpStatus.OK).body(repo.findAllByName(name));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/comments/email/{email}")
    public ResponseEntity<List<Comment>> getCommentByEmail(@PathVariable String email){
        var possibleUser = userRepo.findByEmail(email);
        if(possibleUser.isPresent()){
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
    }

    @PostMapping("/comments/add")
    public ResponseEntity<String> addComment(@RequestBody Comment newComment){
        if(repo.existsById(newComment.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This comment already exists");
        }
        if(isValidComment(newComment))
        repo.save(newComment);
        var certainMovie = movieRepo.findById(newComment.getMovieId());
        if(!certainMovie.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ughhhh");
        Movie mov = certainMovie.get();
        mov.setCommentCount(mov.getCommentCount()+1);
        movieRepo.save(mov);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PutMapping("/comments/update")
    public ResponseEntity<String> updateComment(@RequestBody Comment updatedComment){
        if(updatedComment.getText().length()<=0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insert a comment with a character inside it");
        }
        repo.save(updatedComment);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @DeleteMapping("/comments/delete/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable String id){
        if(!repo.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("the schedule was not found");
        }
        Comment toBeDeleted = repo.findById(id).get();
        repo.delete(toBeDeleted);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    private boolean isValidComment(Comment com)
    {
        var possibleMovie = movieRepo.findById(com.getMovieId());
        var possibleUser = userRepo.findByEmail(com.getEmail());

        return possibleMovie.isPresent()&& possibleUser.isPresent()&&com.getText()!=null&&com.getText().length()>0&&com.getDate()!=null&&com.getId()!=null;
    }

}
