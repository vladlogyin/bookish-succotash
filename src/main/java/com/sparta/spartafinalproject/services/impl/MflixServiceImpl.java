package com.sparta.spartafinalproject.services.impl;

import com.sparta.spartafinalproject.documents.*;
import com.sparta.spartafinalproject.repositories.*;
import com.sparta.spartafinalproject.services.MflixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MflixServiceImpl implements MflixService {

    @Autowired
    MovieRepository movieRepo;
    @Autowired
    CommentRepository commentRepo;
    @Autowired
    TheaterRepository theaterRepo;
    @Autowired
    UserRepository userRepo;

    @Autowired
    ScheduleRepository scheduleRepo;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    @Override
    public Movie getMovieById(String movieId) {
        var possibleMovie =movieRepo.findById(movieId);
        return possibleMovie.isPresent()?possibleMovie.get():null;
    }

    @Override
    public Movie getMovieBySchedule(Schedule s) {
        var possibleMovie =movieRepo.findById(s.getMovieId());
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
        return userRepo.findByEmail(email).get();
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

    @Override
    public void postComment(Comment comment) {
        commentRepo.save(comment);
    }

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
    public void deleteMovieById(String id) {
        var possibleMovie = movieRepo.findById(id);
        if(possibleMovie.isPresent())
            movieRepo.delete(possibleMovie.get());
    }

    @Override
    public void deleteTheaterById(String id) {
        var possibleTheater = theaterRepo.findById(id);
        if(possibleTheater.isPresent())
            theaterRepo.delete(possibleTheater.get());
    }

    @Override
    public void deleteUserById(String id) {
        var possibleUser = userRepo.findById(id);
        if(possibleUser.isPresent())
            userRepo.delete(possibleUser.get());
    }

    @Override
    public void deleteCommentById(String id) {
        var possibleComment = commentRepo.findById(id);
        if(possibleComment.isPresent())
            commentRepo.delete(possibleComment.get());
    }

    @Override
    public void deleteScheduleById(String id) {
        var possibleSchedule = scheduleRepo.findById(id);
        if(possibleSchedule.isPresent())
            scheduleRepo.delete(possibleSchedule.get());
    }
}
