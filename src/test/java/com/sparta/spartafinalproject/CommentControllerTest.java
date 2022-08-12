package com.sparta.spartafinalproject;

import com.sparta.spartafinalproject.documents.Comment;
import com.sparta.spartafinalproject.documents.Movie;
import com.sparta.spartafinalproject.repositories.CommentRepository;
import com.sparta.spartafinalproject.repositories.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.LocalDate;

import static com.sparta.spartafinalproject.TestUtils.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    CommentRepository repo;

    @Autowired
    MongoTemplate mongoTemplate;

    /*
    ```json
    {
            "id": "5a9427648b0beebeb69579e7",
            "name": "John Bishop",
            "date": "1975-01-21T00:31:22.000+00:00",
            "email": "john_bishop@fakegmail.com",
            "movie": "573a1390f29313caabcd446f",
            "text": "Id error ab at molestias dolorum incidunt. Non deserunt praesentium do..."
    }```
     */

    @Test
    void crudTest() throws Exception {
        String commentJSON = "";

        //Movie setup
        Movie testMovie = new Movie();
        testMovie.setId("573a1390f29313caabcd4135");
        testMovie.setCommentCount(0);
        testMovie.setPlot("Three men hammer on an anvil and pass a bottle of beer around.");
        testMovie.setTitle("Blacksmith Scene");
        testMovie.setReleaseDate(Date.valueOf(LocalDate.of(1893, 5, 9)));//"1893-05-09T00:00:00.000+00:00"

        // Comment setup
        Comment testComment = new Comment();
        testComment.setId("573a1390f29313caabcd4135");
        testComment.setName("John Bishop");
        testComment.setDate(Date.valueOf(LocalDate.of(1893, 5, 9)));//1893-05-09T00:00:00.000+00:00
        testComment.setEmail("john_bishop@fakegmail.com");
        testComment.setMovieId("573a1390f29313caabcd4135");
        testComment.setText("Id error ab at molestias dolorum incidunt. Non deserunt praesentium do...");

        if (repo.existsById(testComment.getId())) {
            repo.delete(testComment);
        }

        Assertions.assertFalse(repo.existsById(testComment.getId()), "Comment should not exist before test start");

        //Create
        long countBeforeCreation = repo.count();
        mvc.perform(post("/comments/add")
                .content(asJsonString(testComment))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());
        Assertions.assertEquals(countBeforeCreation + 1, repo.count(), "Count did not increase");

    }
}