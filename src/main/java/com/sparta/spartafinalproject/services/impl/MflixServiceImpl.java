package com.sparta.spartafinalproject.services.impl;

import com.sparta.spartafinalproject.controllers.*;
import com.sparta.spartafinalproject.documents.*;
import com.sparta.spartafinalproject.repositories.*;
import com.sparta.spartafinalproject.services.MflixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Repository
public class MflixServiceImpl implements MflixService {

    @Autowired
    private MovieRepository movieRepo;
    @Autowired
    private CommentRepository commentRepo;
    @Autowired
    private TheaterRepository theaterRepo;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ScheduleRepository scheduleRepo;

    @Autowired
    private MovieController movieCon;
    @Autowired
    private CommentController commentCon; //2022 comment conference?
    @Autowired
    private TheaterController theaterCon;
    @Autowired
    private UserController userCon;
    @Autowired
    private ScheduleController scheduleCon;

    private Random rand = new Random();

    @Override
    public boolean createMovie(Movie mov) {
        if(mov.getId()==null)
        {
            String str="";
            do {
                int randint = rand.nextInt(69420 * 1000);
                str = Base64.getEncoder().encodeToString(("movie" + randint).getBytes());
            }while(movieRepo.findById(str).isPresent());
            mov.setId(str);
        }
        var resp = movieCon.addMovie(mov);
        return resp.getStatusCode()== HttpStatus.OK;
    }

    @Override
    public boolean createTheater(Theater th) {
        if(th.getId()==null)
        {
            String str="";
            do {
                int randint = rand.nextInt(69420 * 1000);
                str = Base64.getEncoder().encodeToString(("theater" + randint).getBytes());
            }while(theaterRepo.findById(str).isPresent());
            th.setId(str);
        }
        var resp = theaterCon.addTheater(th);
        return resp.getStatusCode()==HttpStatus.OK;
    }

    @Override
    public boolean createUser(User user) {
        if(user.getId()==null)
        {
            String str="";
            do {
                int randint = rand.nextInt(69420 * 1000);
                 str = Base64.getEncoder().encodeToString(("user" + randint).getBytes());
            }while(userRepo.findById(str).isPresent());
            user.setId(str);
        }
        var resp = userCon.newUser(user);
        return resp.getStatusCode()==HttpStatus.OK;
    }

    @Override
    public boolean createComment(Comment com) {
        System.out.println("Entered createComment");
        if(com.getId()==null)
        {
            String str="";
            do {
                int randint = rand.nextInt(69420 * 1000);
                str = Base64.getEncoder().encodeToString(("comment" + randint).getBytes());
            }while(commentRepo.findById(str).isPresent());
            com.setId(str);
        }
        var resp = commentCon.addComment(com);

        System.out.println("Leaving createComment");
        return resp.getStatusCode()==HttpStatus.OK;
    }

    @Override
    public boolean createSchedule(Schedule sch) {
        if(sch.getId()==null)
        {
            String str="";
            do {
                int randint = rand.nextInt(69420 * 1000);
                str = Base64.getEncoder().encodeToString(("schedule" + randint).getBytes());
            }while(scheduleRepo.findById(str).isPresent());
            sch.setId(str);
        }
        var resp = scheduleCon.addSchedule(sch);
        return resp.getStatusCode()==HttpStatus.OK;
    }

    @Override
    public List<Movie> getAllMovies() {
        System.out.println("Trying to get movies");
        var revMovies= movieCon.getAllMovies();
        System.out.println("Reversing the list...");
        Collections.reverse(revMovies);
        System.out.println("Got movies");
        return revMovies.subList(0,100);

    }

    @Override
    public Movie getMovieById(String movieId) {
        var possibleMovie = movieRepo.findById(movieId);
        return possibleMovie.isPresent()?possibleMovie.get():null;
    }

    @Override
    public Movie getMovieBySchedule(Schedule s) {
        var possibleMovie = movieRepo.findById(s.getMovieId());
        return possibleMovie.isPresent()?possibleMovie.get():null;
    }

    @Override
    public List<Theater> getAllTheaters() {
        return theaterRepo.findAll();
    }

    @Override
    public Theater getTheaterById(String id) {
        var possibleTheater = theaterRepo.findById(id);
        return possibleTheater.isPresent()?possibleTheater.get():null;
    }

    @Override
    public Theater getTheaterByTheaterId(int id) {
        var possibleTheater = theaterRepo.findByTheaterId(id);
        return possibleTheater.isPresent()?possibleTheater.get():null;
    }

    @Override
    public Theater getTheaterBySchedule(Schedule s) {
        return getTheaterById(s.getTheaterId());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        var possibleUser = userRepo.findByEmail(email);
        return possibleUser.isPresent()?possibleUser.get():null;
    }

    @Override
    public User getUserById(String id) {
        var possibleUser = userRepo.findById(id);
        return possibleUser.isPresent()?possibleUser.get():null;
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }

    @Override
    public List<Comment> getCommentsByMovie(Movie movie) {
        return commentRepo.findAllByMovieId(movie.getId());
    }
    @Override
    public List<Comment> getCommentsByMovieId(String movieId) {
        return commentRepo.findAllByMovieId(movieId);
    }

    @Override
    public Comment getCommentById(String id) {
        return commentRepo.findById(id).get();
    }

    /*@Override
    public void postComment(Comment comment) {
        commentRepo.save(comment);
    }*/

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepo.findAll();
    }

    @Override
    public Schedule getScheduleById(String id) {
        var possibleSchedule = scheduleRepo.findById(id);
        return possibleSchedule.isPresent()?possibleSchedule.get():null;
    }

    @Override
    public List<Schedule> getSchedulesByMovie(Movie movie) {
        return scheduleRepo.findAllByMovieId(movie.getId());
    }

    @Override
    public List<Schedule> getSchedulesByMovieId(String movieId) {
        return scheduleRepo.findAllByMovieId(movieId);
    }

    @Override
    public List<Schedule> getSchedulesByTime(String time) {
        return scheduleRepo.findAllByTime(time);
    }

    @Override
    public List<Schedule> getSchedulesByTheater(Theater th) {
        return scheduleRepo.findAllByTheaterId(th.getId());
    }


    @Override
    public boolean updateMovie(Movie mov) {
        var resp = movieCon.updateMovie(mov);
        return resp.getStatusCode()==HttpStatus.OK;
    }

    @Override
    public boolean updateTheater(Theater th) {
        var resp = theaterCon.updateTheater(th);
        return resp.getStatusCode()==HttpStatus.OK;
    }

    @Override
    public boolean updateUser(User user) {
        var resp = userCon.updateUser(user);
        return resp.getStatusCode()==HttpStatus.OK;
    }

    @Override
    public boolean updateComment(Comment com) {
        var resp = commentCon.updateComment(com);
        return resp.getStatusCode()==HttpStatus.OK;
    }

    @Override
    public boolean updateSchedule(Schedule sch) {
        var resp = scheduleCon.updateSchedule(sch);
        return resp.getStatusCode()==HttpStatus.OK;
    }

    @Override
    public boolean deleteMovieById(String id) {
        var possibleMovie = movieRepo.findById(id);
        if(possibleMovie.isPresent()) {
            movieRepo.delete(possibleMovie.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTheaterById(String id) {
        var possibleTheater = theaterRepo.findById(id);
        if(possibleTheater.isPresent()) {
            theaterRepo.delete(possibleTheater.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserById(String id) {
        var possibleUser = userRepo.findById(id);
        if(possibleUser.isPresent()) {
            userRepo.delete(possibleUser.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCommentById(String id) {
        var possibleComment = commentRepo.findById(id);
        if(possibleComment.isPresent())
        {
            commentRepo.delete(possibleComment.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteScheduleById(String id) {
        var possibleSchedule = scheduleRepo.findById(id);
        if(possibleSchedule.isPresent()) {
            scheduleRepo.delete(possibleSchedule.get());
            return true;
        }
        return false;
    }
}
