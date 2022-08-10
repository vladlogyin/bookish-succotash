package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.*;
import com.sparta.spartafinalproject.services.MflixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NavigationController {

    @Autowired
    MflixService api;
    //_________POST_____________
    @GetMapping("/createcomment")
    public String createComment(){
        return "/comments/createcomment";
    }
    @GetMapping("/createtheatre")
    public String createTheatre(){
        return "/theaters/createtheatre";
    }
    @GetMapping("/createuser")
    public String createUser(Model model){
        User user = new User();
        model.addAttribute("newUser",user);
        return "/users/createuser";
    }
/*    @PostMapping("/createueer/post")
    public String commitUsertoDatabase(@RequestBody ){

    }*/

    @GetMapping("/createmovie")
    public String createMovie(Model model){
        Movie movie = new Movie();
        model.addAttribute("newMovie", movie);
        return "/movies/createmovie";
    }
    @GetMapping("/createschedule")
    public String createSchedule(){
        return "/schedules/createschedule";
    }

    //__________UPDATE____________
    @GetMapping("/updatecomments/{id}")
    public String updateCommentById(@PathVariable String id , Model model){
        model.addAttribute("id", id);
        return "/comments/updatecommentbyid";
    }
    @GetMapping("/updatetheaters/{id}")
    public String updateTheatreById(@PathVariable String id , Model model){
        model.addAttribute("id", id);
        return "/theaters/updatetheatrebyid";
    }
    @GetMapping("/updateusers/{id}")
    public String updateUserById(@PathVariable String id , Model model){
        model.addAttribute("id", id);
        return "/users/updateuserbyid";
    }
    @GetMapping("/updateschedules/{id}")
    public String updateScheduleById(@PathVariable String id , Model model){
        model.addAttribute("id", id);
        return "/schedules/updateschedulebyid";
    }

    @GetMapping("/updatemovies/{id}")
    public String updatemovies(@PathVariable String id, Model model){
        model.addAttribute("id", id);
        return "/movies/updatemoviebyid";
    }


    //__________GET ALL____________
    @GetMapping("/comments/all")
    public String displayAllComments(Model model){
        List<Comment> comments = api.getAllComments();
        List <List<String>> fields = new ArrayList<>();
        List <String> temp = new ArrayList<>();
        getVolcano();
        for (Comment c : comments){
            temp = new ArrayList<>();

            temp.add(c.getId());
            temp.add(c.getName());
            temp.add(String.valueOf(c.getDate()));
            temp.add(c.getEmail());

            fields.add(temp); //commit list to overall list at end
        }
        String[] fieldNames ={"id","Name","Date","Email"};

        model.addAttribute("entityList" , fields);
        model.addAttribute("fieldNames", fieldNames);
        model.addAttribute("title", "All comments");
        model.addAttribute("getExtension", "displaycomments");
        model.addAttribute("creationExtension", "createcomment");
        model.addAttribute("updateExtension", "updatecomments");
        model.addAttribute("deleteExtension", "deletecomments");
        return "data";
    }
    @GetMapping("/theaters/all")
    public String displayAllTheaters(Model model){
        List<Theater> theaters = api.getAllTheaters();
        List <List<String>> fields = new ArrayList<>();
        List <String> temp;
        for (Theater t : theaters){
            temp = new ArrayList<>();

            temp.add(t.getId());
            temp.add(t.getTheaterId()/*spooky in-line comment*/+"");
            temp.add(String.valueOf(t.getLocation().getAddress()));

            fields.add(temp); //commit list to overall list at end
        }
        String[] fieldNames ={"String ID","Numerical ID","Address"};

        model.addAttribute("entityList" , fields);
        model.addAttribute("fieldNames", fieldNames);
        model.addAttribute("title", "All theaters");
        model.addAttribute("getExtension", "displaytheaters");
        model.addAttribute("creationExtension", "createtheater");
        model.addAttribute("updateExtension", "updatetheaters");
        model.addAttribute("deleteExtension", "deletetheaters");
        return "data";
    }
    @GetMapping("/users/all")
    public String displayAllUsers(Model model){
        List<User> users = api.getAllUsers();
        List <List<String>> fields = new ArrayList<>();
        List <String> temp = new ArrayList<>();
        for (User u : users){
            temp = new ArrayList<>();

            temp.add(u.getId());
            temp.add(u.getName());
            temp.add(u.getEmail());

            fields.add(temp); //commit list to overall list at end
        }
        String[] fieldNames ={"id","Name","Email"};

        model.addAttribute("entityList" , fields);
        model.addAttribute("fieldNames", fieldNames);
        model.addAttribute("title", "All users");
        model.addAttribute("getExtension", "displayusers");
        model.addAttribute("creationExtension", "createuser");
        model.addAttribute("updateExtension", "updateusers");
        model.addAttribute("deleteExtension", "deleteusers");
        return "data";
    }
    @GetMapping("/schedules/all")
    public String displayAllSchedules(Model model){
        List<Schedule> schedules= api.getAllSchedules();
        List <List<String>> fields = new ArrayList<>();
        List <String> temp = new ArrayList<>();
        for (Schedule s : schedules){
            temp = new ArrayList<>();
            Movie mov = api.getMovieBySchedule(s);
            Theater th = api.getTheaterBySchedule(s);
            temp.add(s.getId()); //<<<<< A user doesn't need to see this
            temp.add(mov.getTitle());
            temp.add(String.valueOf(th==null?"N/A":th.getTheaterId())); // *sparkles* Name *sparkles*
            temp.add(String.valueOf(s.getTime()));

            fields.add(temp); //commit list to overall list at end
        }
        String[] fieldNames ={"id","Movie Title","Sparta theater no#","Time"};

        model.addAttribute("entityList" , fields);
        model.addAttribute("fieldNames", fieldNames);
        model.addAttribute("title", "All schedules");
        model.addAttribute("getExtension", "displayschedules");
        model.addAttribute("creationExtension", "createschedule");
        model.addAttribute("updateExtension", "updateschedules");
        model.addAttribute("deleteExtension", "deleteschedules");
        return "data";
    }

    @GetMapping("/movies/all")
    public String displayAllMovies(Model model){
        List<Movie> movies = api.getAllMovies();
        List <List<String>> fields = new ArrayList<>();
        List <String> temp = new ArrayList<>();
        for (Movie m : movies){
            temp = new ArrayList<>();

            temp.add(m.getId());
            temp.add(m.getTitle());
            temp.add(String.valueOf(m.getReleaseDate()==null?null:(m.getReleaseDate().getYear()+1900)));
            temp.add(String.valueOf(m.getCommentCount()));


            fields.add(temp); //commit list to overall list at end
        }
        String[] fieldNames ={"id","Title","Release date","Comment count"};

        model.addAttribute("entityList" , fields);
        model.addAttribute("fieldNames", fieldNames);
        model.addAttribute("title", "All Movies");
        model.addAttribute("getExtension", "displaymovies");
        model.addAttribute("creationExtension", "createmovie");
        model.addAttribute("updateExtension", "updatemovies");
        model.addAttribute("deleteExtension", "deletemoviess");
        return "data";
    }


    //__________GET BY ID_____________

    //  TODO Get rid of this VVVV
    @GetMapping("/displaycomments/{id}")
    public String displayCommentById(@PathVariable String id , Model model){
        model.addAttribute("id", id);

        return "displaycommentbyid";
    }
    @GetMapping("/displaytheaters/{id}")
    public String displayTheatersById(@PathVariable String id, Model model){
        model.addAttribute("id", id);
        return "displaytheatrebyid";
    }
    @GetMapping("/displayusers/{id}")
    public String displayUsersById(@PathVariable String id, Model model){
        model.addAttribute("id", id);
        return "displayuserbyid";
    }
    @GetMapping("/displayschedules/{id}")
    public String displayAllSchedules(@PathVariable String id, Model model){
        model.addAttribute("id", id);
        return "displayschedulebyid";
    }
    @GetMapping("/displaymovies/{id}")
    public String displayAllMovies(@PathVariable String id, Model model){
        Movie movie = api.getMovieById(id);
        List<Comment> comments = api.getCommentsByMovie(movie);
        List<Schedule> schedules = api.getSchedulesByMovie(movie);

        model.addAttribute("movie", movie);
        model.addAttribute("comments" , comments);
        model.addAttribute("schedules", schedules);

        return "movies/displayschedulebyid";
    }

    //__________DELETE BY ID___________
    @GetMapping("/deletecomments/{id}")
    public ModelAndView deleteCommentsById(@PathVariable String id , Model model, ModelMap modelMap) {
        api.deleteCommentById(id);
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/comments/all", modelMap);
    }
    @GetMapping("/deletetheaters/{id}")
    public  ModelAndView deleteTheatersById(@PathVariable String id , Model model, ModelMap modelMap) {
        api.deleteTheaterById(id);
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/theaters/all", modelMap);
    }
    @GetMapping("/deleteusers/{id}")
    public  ModelAndView deleteUsersById(@PathVariable String id , Model model, ModelMap modelMap) {
        api.deleteUserById(id);
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/users/all", modelMap);
    }
    @GetMapping("/deleteschedules/{id}")
    public  ModelAndView deleteSchedulesById(@PathVariable String id , Model model, ModelMap modelMap) {
        api.deleteScheduleById(id);
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/schedules/all", modelMap);
    }
    @GetMapping("/deletemovies/{id}")
    public  ModelAndView deleteMoviesById(@PathVariable String id , Model model, ModelMap modelMap) {
        api.deleteMovieById(id);
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/moviess/all", modelMap);
    }

    public void getVolcano(){
        //----\
        //     \
        //==   /
        //----/
        {
            {
                {
                    {if(true);
                        {if(true);
                            {if(true);
                                {if(true);
                                    {if(true);
                                        {if(true);
                                            {if(true);
                                                {if(true);
                                                    {if(true);
                                                        {if(true);
                                                            {if(true);                               ;;;;;;;;;;;;;;;;;;
                                                                {if(true);                          ;;;;;;;;;;;;;;;;;;
                                                                    {if(true);              ;;;;;;;;;;;;;;;;;;;;;;
                                                                        {if(true);  ;;;;;;;;;;;;;;;;;;;;;;
                                                                            ;;;;;;;;;; //horizontal volcano?
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
