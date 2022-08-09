package com.sparta.spartafinalproject;

import com.sparta.spartafinalproject.documents.Movie;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static com.sparta.spartafinalproject.TestUtils.asJsonString;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    MovieRepository repo;

    @Autowired
    MongoTemplate mongoTemplate;
/*

```json
{
    "id": "573a1390f29313caabcd4135",
    "commentCount": 0,
    "plot": "Three men hammer on an anvil and pass a bottle of beer around.",
    "title": "Blacksmith Scene",
    "releaseDate": "1893-05-09T00:00:00.000+00:00"
}```

 */

    @Test
    void crudTest() throws Exception
    {
        String movieJSON="";
        //Test setup
        Movie testMovie = new Movie();
        testMovie.setId("573a1390f29313caabcd4135");
        testMovie.setCommentCount(0);
        testMovie.setPlot("Three men hammer on an anvil and pass a bottle of beer around.");
        testMovie.setTitle("Blacksmith Scene");
        testMovie.setReleaseDate(Date.valueOf(LocalDate.of(2077,1,1)));//"1893-05-09T00:00:00.000+00:00"

        if(repo.existsById(testMovie.getId()))
        {
           repo.delete(testMovie);
        }

        Assertions.assertFalse(repo.existsById(testMovie.getId()), "Movie should not exist before test start");

        //Create
        long countBeforeCreation = repo.count();
        mvc.perform(post("/movies/add")
                .content(asJsonString(testMovie))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());
        Assertions.assertEquals(countBeforeCreation+1,repo.count(),"Count did not increase");

        //Request
        mvc.perform(get("/movies/id/"+testMovie.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(asJsonString(testMovie)));
        //Update


    }



}
