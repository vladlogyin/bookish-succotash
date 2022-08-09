package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.User;
import com.sparta.spartafinalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User getUserById(@PathVariable String id){
        return repo.findById(id).get();

    }
    @GetMapping("/users/by-name/{name}")
    public User getUserByName(@PathVariable String name){
        return repo.findByName(name);
    }
    @GetMapping("/users/by-email/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email){
        return repo.findByEmail(email);
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
    public User newUser(@RequestBody User newUser){
        repo.save(newUser);
        return newUser;
    }

    @PatchMapping("/user/update/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User updateUser){
        User user = repo.findById(id).get();
        user.setEmail(updateUser.getEmail());
        user.setName(updateUser.getName());
        user.setPassword(updateUser.getPassword());
        return repo.save(user);
    }


}
