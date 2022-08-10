package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.Comment;
import com.sparta.spartafinalproject.documents.Movie;
import com.sparta.spartafinalproject.documents.Theater;
import com.sparta.spartafinalproject.documents.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NavigationController {

    //_________POST_____________
    @GetMapping("/createcomment")
    public String createComment(){
        return "/comments/createcomment";
    }
    @GetMapping("/createtheatre")
    public String createTheatre(){
        return "/theatres/createtheatre";
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
    @GetMapping("/updatetheatres/{id}")
    public String updateTheatreById(@PathVariable String id , Model model){
        model.addAttribute("id", id);
        return "/theatres/updatetheatrebyid";
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
        //List<Comment> comments = todo mvc.getallcomments();
        List <List<String>> fields = new ArrayList<>();
        List <String> temp = new ArrayList<>();
        for (Comment c : comments){
            temp.clear(); //empty the list first

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
        model.addAttribute("creationExtension", "createcomment");
        model.addAttribute("updateExtension", "updatecomments");
        model.addAttribute("deleteExtension", "deletecomments");
        return "data";
    }
    @GetMapping("/theatres/all")
    public String displayAllTheatres(Model model){
        //List<Theatre> theaters = todo mvc.getalltheatres();
        List <List<String>> fields = new ArrayList<>();
        List <String> temp = new ArrayList<>();
        for (Theater c : theaters){
            temp.clear(); //empty the list first

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
        model.addAttribute("creationExtension", "createcomment");
        model.addAttribute("updateExtension", "updatecomments");
        model.addAttribute("deleteExtension", "deletecomments");
        return "data";
    }
    @GetMapping("/users/all")
    public String displayAllUsers(Model model){
//List<Comment> comments = todo mvc.getallcomments();
        List <List<String>> fields = new ArrayList<>();
        List <String> temp = new ArrayList<>();
        for (Comment c : comments){
            temp.clear(); //empty the list first

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
        model.addAttribute("creationExtension", "createcomment");
        model.addAttribute("updateExtension", "updatecomments");
        model.addAttribute("deleteExtension", "deletecomments");
        return "data";
    }
    @GetMapping("/schedules/all")
    public String displayAllSchedules(Model model){
//List<Comment> comments = todo mvc.getallcomments();
        List <List<String>> fields = new ArrayList<>();
        List <String> temp = new ArrayList<>();
        for (Comment c : comments){
            temp.clear(); //empty the list first

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
        model.addAttribute("creationExtension", "createcomment");
        model.addAttribute("updateExtension", "updatecomments");
        model.addAttribute("deleteExtension", "deletecomments");
        return "data";
    }

    //__________GET BY ID_____________
    @GetMapping("/displaycomments/{id}")
    public String displayCommentById(@PathVariable String id , Model model){
        model.addAttribute("id", id);
        return "displaycommentbyid";
    }
    @GetMapping("/displaytheatres/{id}")
    public String displayTheatresById(@PathVariable String id, Model model){
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

    //__________DELETE BY ID___________
    @GetMapping("/deletecomments/{id}")
    public  String deleteCommentsById(@PathVariable String id , Model model) {
        model.addAttribute("id", id);
        return "deletecommentsbyid";
    }
    @GetMapping("/deletetheatres/{id}")
    public  String deleteTheatresById(@PathVariable String id , Model model) {
        model.addAttribute("id", id);
        return "deletetheatresbyid";
    }
    @GetMapping("/deleteusers/{id}")
    public  String deleteUsersById(@PathVariable String id , Model model) {
        model.addAttribute("id", id);
        return "deleteusersbyid";
    }
    @GetMapping("/deleteschedules/{id}")
    public  String deleteSchedulesById(@PathVariable String id , Model model) {
        model.addAttribute("id", id);
        return "deleteschedulesbyid";
    }


}
