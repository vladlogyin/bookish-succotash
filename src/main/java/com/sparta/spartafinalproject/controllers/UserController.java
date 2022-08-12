package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.User;
import com.sparta.spartafinalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository repo;

    @GetMapping("/user/all")
    public List<User> getAllUsers() {
        return repo.findAll();
    }
    @GetMapping("/users/by-id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        if(repo.existsById(id)){
            return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id).get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @GetMapping("/users/by-email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        if(repo.existsByEmail(email)){
            return  ResponseEntity.status(HttpStatus.OK).body(repo.findByEmail(email).get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/user/delete/{id}")
        public void deleteUserById(@PathVariable String id) {
        User user = repo.findById(id).get();
        repo.delete(user);
    }
    @DeleteMapping("/user/delete/{name}")
    public void deleteUserByName(@PathVariable String name) {
        User user = repo.findByName(name);
        repo.delete(user);
    }
    @PostMapping("/user/new")
    public ResponseEntity<String> newUser(@RequestBody User newUser){
        if(repo.existsByEmail(newUser.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("this email already exists");
        }
        if(repo.existsById(newUser.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("this user already exists");
        }
        if(!isValidUser(newUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please fix this now...");
        }
        repo.save(newUser);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PostMapping("/user/update")
    public ResponseEntity updateUser(@RequestBody User updateUser){

        if(!isValidUser(updateUser))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please fix this now...");
        repo.save(updateUser);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    private boolean isValidUser(User user)
    {
        var possibleDuplicate = repo.findByEmail(user.getEmail());
        return !(possibleDuplicate.isPresent()&&possibleDuplicate.get().getId()!=user.getId());
    }


}
