package com.sparta.spartafinalproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartafinalproject.documents.User;
import com.sparta.spartafinalproject.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.net.URL;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    UserRepository repo;

    @Autowired
    MongoTemplate mongoTemplate;
    private ObjectMapper mapper = new ObjectMapper();
    String serverURL = "http://localhost";

    @Test
    @DisplayName("Get user by ID")
    void getById() {
        try {
            User result = mapper.readValue(
                    new URL(serverURL + "/user/id/59b99db5cfa9a34dcd7885b8"),
                    User.class);
            Assertions.assertEquals("59b99db5cfa9a34dcd7885b8", result.getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Get user by name")
    void getByName() {
        try {
            User result = mapper.readValue(
                    new URL(serverURL + "/user/id/59b99db5cfa9a34dcd7885b8"),
                    User.class);
            Assertions.assertEquals("Jaime Lannister", result.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Get user by email")
    void getByEmail(){
        try {
            User result = mapper.readValue(
                    new URL(serverURL + "/user/id/59b99db5cfa9a34dcd7885b8"),
                    User.class);
            Assertions.assertEquals("nikolaj_coster-waldau@gameofthron.es", result.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
