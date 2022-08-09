package com.sparta.spartafinalproject;

import com.sparta.spartafinalproject.repositories.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
class SpartaFinalProjectApplicationTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private MovieRepository movieRepo;

    @Test
    @DisplayName("Performing movie CRUD tests")
    void movieCRUDTest() throws Exception{
        long movieCount = movieRepo.count();
        Assertions.assertTrue(movieCount>0,"Internal error getting movie count");

        mvc.perform(get("/movies/count"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(String.valueOf(movieCount)))
                ;

        mvc.perform(
                post("/movie/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{title:\"Studio 666\"}")
        ).andExpect(status().is2xxSuccessful());
    }

}
