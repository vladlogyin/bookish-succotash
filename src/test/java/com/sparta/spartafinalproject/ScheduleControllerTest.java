package com.sparta.spartafinalproject;


import com.sparta.spartafinalproject.documents.Movie;
import com.sparta.spartafinalproject.documents.Schedule;
import com.sparta.spartafinalproject.documents.Theater;
import com.sparta.spartafinalproject.repositories.MovieRepository;
import com.sparta.spartafinalproject.repositories.ScheduleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.sparta.spartafinalproject.TestUtils.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ScheduleControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ScheduleRepository repo;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void crudTest() throws Exception {
        String scheduleJSON="";
        //Test Setup
        Schedule testSchedule = new Schedule();
        testSchedule.setId("123456789");
        testSchedule.setTime("12:00");
        testSchedule.setMovieId("573a1390f29313caabcd4135");
        testSchedule.setTheaterId("");

        if(repo.existsById(testSchedule.getId())){
            repo.delete(testSchedule);
        }

        Assertions.assertFalse(repo.existsById(testSchedule.getId()),"Movie should not exist before test start");

        //create
        long countBeforeCreation = repo.count();
        mvc.perform(post("/schedule/add")
                .content(asJsonString(testSchedule))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError());
        Assertions.assertEquals(countBeforeCreation+1,repo.count(),"count did not increase");

        //Request
        mvc.perform(get("/schedule/by-id/"+testSchedule.getId()))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(asJsonString(testSchedule)));
    }
}
