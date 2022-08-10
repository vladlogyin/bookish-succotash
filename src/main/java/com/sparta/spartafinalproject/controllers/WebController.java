package com.sparta.spartafinalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/home")
    public String displayHomepage(){
        return "home";
    }
    @GetMapping("/login")
    public String logInForm(){
        return "security/login";
    }
    @GetMapping("/logout")
    public String loggingOut(){
        return "security/logout";
    }

    @GetMapping("/accessDenied")
    public String accessDeniedPage(){ return "security/accessDenied"; }
}
