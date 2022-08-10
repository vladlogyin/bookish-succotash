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
    /* TODO is this necessary? Name is not a unique property
    @GetMapping("/users/by-name/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name){
        if(repo.existsByNamen(name)){
            return ResponseEntity.status(HttpStatus.OK).body(repo.findByName(name));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } */
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
        // TODO check if email already exists (assuming email is a unique key)
        if(repo.existsById(newUser.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("this user already exists");
        }
        repo.save(newUser);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PatchMapping("/user/update/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User updateUser){
        User user = repo.findById(id).get(); // TODO if the ID already matches, it should be enough to "just" persist the user to the database
        user.setEmail(updateUser.getEmail());
        user.setName(updateUser.getName());
        user.setPassword(updateUser.getPassword());
        return repo.save(user);
    }


}
