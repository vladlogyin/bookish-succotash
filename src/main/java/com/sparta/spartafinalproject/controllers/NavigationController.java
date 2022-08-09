package com.sparta.spartafinalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NavigationController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    //_________POST_____________
    @GetMapping("/createcomment")
    public String createComment(){
        return "createcomment";
    }
    @GetMapping("/createtheatre")
    public String createTheatre(){
        return "createtheatre";
    }
    @GetMapping("/createuser")
    public String createUser(){
        return "createuser";
    }
    @GetMapping("/createschedule")
    public String createSchedule(){
        return "createschedule";
    }

    //__________UPDATE____________
    @GetMapping("/updatecomments/{id}")
    public String updateCommentById(@PathVariable String id , Model model){
        model.addAttribute("id", id);
        return "updatecommentbyid";
    }
    @GetMapping("/updatetheatres/{id}")
    public String updateTheatreById(@PathVariable String id , Model model){
        model.addAttribute("id", id);
        return "updatetheatrebyid";
    }
    @GetMapping("/updateusers/{id}")
    public String updateUserById(@PathVariable String id , Model model){
        model.addAttribute("id", id);
        return "updateuserbyid";
    }
    @GetMapping("/updatecschedules/{id}")
    public String updateScheduleById(@PathVariable String id , Model model){
        model.addAttribute("id", id);
        return "updateSchedulesbyid";
    }


    //__________GET ALL____________
    @GetMapping("/comments/all")
    public String displayAllComments(){
        return "displayallcomments";
    }
    @GetMapping("/theatres/all")
    public String displayAllTheatres(){
        return "displayalltheatres";
    }
    @GetMapping("/users/all")
    public String displayAllUsers(){
        return "displayallusers";
    }
    @GetMapping("/schedules/all")
    public String displayAllSchedules(){
        return "displayallschedules";
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
