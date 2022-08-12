package com.sparta.spartafinalproject.controllers;

import com.sparta.spartafinalproject.documents.*;
import com.sparta.spartafinalproject.services.MflixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class NavigationController {

    @Autowired
    MflixService api;
    //_________POST_____________
    //___COMMENT___
    @GetMapping("/createcomment")
    public String createComment(Model model){
        Comment comment = new Comment();
        model.addAttribute("newComment",comment);
        return "/comments/createcomment";
    }
    @PostMapping("/createcomment/post")
    public ModelAndView commitCommentToDatabase(@ModelAttribute Comment newComment){
        System.out.println(newComment.getText());
        System.out.println(newComment.getName());
        System.out.println(newComment.getEmail());
        System.out.println(newComment.getMovieId());
        newComment.setDate(new Date());
        boolean result = api.createComment(newComment); //false if this failed
        System.out.println("Created comment?"+result);
        ModelAndView mv = new ModelAndView("redirect:/displaymovies/"+newComment.getMovieId());
        mv.addObject("attribute", "redirectWithRedirectPrefix");
        return mv;
    }
    //__THEATER__
    @GetMapping("/createtheater")
    public String createTheater(Model model){
        Theater theater = new Theater();
        model.addAttribute("newTheater",theater);
        return "/theaters/createtheater";
    }
    @PostMapping("/createtheater/post")
    public ModelAndView commitTheaterToDatabase(@ModelAttribute Theater theater, ModelMap modelMap, Model model){
        api.createTheater(theater);
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/theaters/all", modelMap);
    }
    //____USER____
    @GetMapping("/createuser")
    public String createUser(Model model){
        User user = new User();
        model.addAttribute("newUser",user);
        return "/users/createuser";
    }
    @PostMapping("/createuser/post")
    public ModelAndView commitUserToDatabase(@ModelAttribute User user, ModelMap modelMap, Model model){
            api.createUser(user);
            model.addAttribute("attribute", "redirectWithRedirectPrefix");
            return new ModelAndView("redirect:/users/all", modelMap);
    }

    //____MOVIE____
    @GetMapping("/createmovie")
    public String createMovie(Model model){
        Movie movie = new Movie();
        model.addAttribute("newMovie", movie);
        return "/movies/createmovie";
    }
    @PostMapping("/createmovie/post")
    public ModelAndView commitMovieToDatabase(@ModelAttribute Movie movie, ModelMap modelMap, Model model){
        api.createMovie(movie);
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/movies/all", modelMap);
    }
    //__SCHEDULE__
    @GetMapping("/createschedule")
    public String createSchedule(Model model){
        Schedule schedule = new Schedule();
        model.addAttribute("newSchedule",schedule);
        return "/schedules/createschedule";
    }
    @PostMapping("/createschedule/post")
    public ModelAndView commitScheduleToDatabase(@ModelAttribute Schedule schedule, ModelMap modelMap, Model model){
        api.createSchedule(schedule);
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/schedules/all", modelMap);
    }

    //__________UPDATE____________//
    //____COMMENT____//
    @GetMapping("/updatecomments/{id}")
    public String updateCommentById(@PathVariable String id , Model model){
        Comment comment = api.getCommentById(id);
        model.addAttribute("commentToEdit",comment);
        return "/comments/updatecommentbyid";
    }
    @PostMapping("/updatecomments/update")
    public ModelAndView updateCommentInDatabase(@ModelAttribute Comment comment, ModelMap modelMap, Model model){
        api.updateComment(comment);
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/comments/all", modelMap);
    }

    //____THEATER____//
    @GetMapping("/updatetheaters/{id}")
    public String updateTheaterById(@PathVariable String id , Model model){
        Theater theater = api.getTheaterById(id);
        model.addAttribute("theaterToEdit",theater);
        return "/theaters/updatetheaterbyid";
    }
    @PostMapping("/updatetheaters/update")
    public ModelAndView updateTheatertInDatabase(@ModelAttribute Theater theater, ModelMap modelMap, Model model){
        boolean result = api.updateTheater(theater);//do somethign
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/theaters/all", modelMap);
    }

    //_____USER____
    @GetMapping("/updateusers/{id}")
    public String updateUserById(@PathVariable String id , Model model){
        User user = api.getUserById(id);
        model.addAttribute("userToEdit", user);
        return "/users/updateuserbyid";
    }
    @PostMapping("/updateusers/update")
    public ModelAndView updateUsertInDatabase(@ModelAttribute User user, ModelMap modelMap, Model model){
        api.updateUser(user);
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/users/all", modelMap);
    }

    //___SCHEDULE___//
    @GetMapping("/updateschedules/{id}")
    public String updateScheduleById(@PathVariable String id , Model model){
        Schedule schedule = api.getScheduleById(id);
        model.addAttribute("scheduleToEdit", schedule);
        return "/schedules/updateschedulebyid";
    }
    @PostMapping("/updateschedules/update")
    public ModelAndView updateScheduletInDatabase(@ModelAttribute Schedule scheduleToEdit, ModelMap modelMap, Model model){
        boolean success = api.updateSchedule(scheduleToEdit);
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        if(success) {
            return new ModelAndView("redirect:/schedules/all", modelMap);
        }
        else
        {
            return new ModelAndView("redirect:/mistake", modelMap);
        }
    }

    //_____MOVIE_____//
    @GetMapping("/updatemovies/{id}")
    public String updatemovies(@PathVariable String id, Model model){
        Movie movieToEdit = api.getMovieById(id);

        model.addAttribute("movieToEdit", movieToEdit);
        return "/movies/updatemoviebyid";
    }
    @PostMapping("/updatemovies/update")
    public ModelAndView updateMovieInDatabase(@ModelAttribute Movie movie, ModelMap modelMap, Model model){
        //todo: api.patchMovie()
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/movies/all", modelMap);
    }


    //__________GET ALL____________//
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
        model.addAttribute("deleteExtension", "deletemovies");
        return "data";
    }


    //__________GET BY ID_____________//


    @GetMapping("/displaycomments/{id}")
    public String displayCommentById(@PathVariable String id , Model model){
        Comment comment = api.getCommentById(id);
        Movie movie = api.getMovieById(comment.getMovieId());
        if (movie==null){
            movie=new Movie();
        }
        model.addAttribute("movie",movie);
        model.addAttribute("comment", comment);
        return "/comments/displaysinglecomment";
    }
    @GetMapping("/displaytheaters/{id}")
    public String displayTheatersById(@PathVariable String id, Model model){
        Theater theater = api.getTheaterById(id);
        List<Schedule> schedules=api.getSchedulesByTheater(theater);
        List<Movie> movies= new ArrayList<>();
        for (Schedule s : schedules){
            if(api.getMovieBySchedule(s)!=null) {
                movies.add(api.getMovieBySchedule(s));
            }
        }

        model.addAttribute("movies", movies);
        model.addAttribute("theater",theater);
        return "/theaters/displaysingletheater";
    }
    @GetMapping("/displayusers/{id}")
    public String displayUsersById(@PathVariable String id, Model model){
        User user = api.getUserById(id);
        model.addAttribute("user",user);
        return "/users/displaysingleuser";
    }
    @GetMapping("/displayschedules/{id}")
    public String displayScheduleById(@PathVariable String id, Model model){
        Schedule schedule = api.getScheduleById(id);
        Movie movie = api.getMovieBySchedule(schedule);
        Theater theater = api.getTheaterBySchedule(schedule);
        List<Schedule> movieSchedule = api.getSchedulesByMovie(movie);
        List<Comment> movieComments = api.getCommentsByMovie(movie);
        model.addAttribute("comments",movieComments);
        model.addAttribute("schedules",movieSchedule);
        model.addAttribute("movie", movie);
        model.addAttribute("theater", theater);
        return "movies/displaysinglemovie";
    }
    @GetMapping("/displaymovies/{id}")
    public String displayMovieById(@PathVariable String id, Model model){
        Movie movie = api.getMovieById(id);
        List<Schedule> movieSchedule = api.getSchedulesByMovie(movie);
        List<Schedule> cleanSchedules = new ArrayList<>();
        for (Schedule s : movieSchedule) {
            if (s.getTheaterId()!=null && s.getTime()!=null)
            cleanSchedules.add(s);
        }
        List<Comment> movieComments = api.getCommentsByMovie(movie);
        Comment com = new Comment();
        com.setMovieId(movie.getId());
        com.setEmail("christian_williams@fakegmail.com");
        com.setName("Christian Williams");
        model.addAttribute("newComment", com);
        model.addAttribute("comments",movieComments);
        model.addAttribute("api",api);
        model.addAttribute("schedules",cleanSchedules);
        model.addAttribute("movie", movie);
        return "movies/displaysinglemovie";
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
        //----\         _________________
        //     \       /   Oh Noes!!!   /
        //==   /   ___/________________/
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
