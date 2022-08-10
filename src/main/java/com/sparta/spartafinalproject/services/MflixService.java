package com.sparta.spartafinalproject.services;

import com.sparta.spartafinalproject.documents.*;

import java.util.List;

public interface MflixService {
    List<Movie> getAllMovies();
    Movie getMovieById(String movieId);
    Movie getMovieBySchedule(Schedule s);

    List<Theater> getAllTheaters();
    Theater getTheaterById(String id);
    Theater getTheaterByTheaterId(int id);

    Theater getTheaterBySchedule(Schedule s);
    List<User> getAllUsers();
    User getUserByEmail(String email);
    List<Comment> getAllComments();
    List<Comment> getCommentsByMovie(Movie movie);
    List<Comment> getCommentsByMovieId(String movieId);
    Comment getCommentById(String id);
    void postComment(Comment comment);

    List<Schedule> getAllSchedules();
    Schedule getScheduleById(String id);

    List<Schedule> getSchedulesByMovie(Movie movie);
    List<Schedule> getSchedulesByMovieId(String movieId);
    List<Schedule> getSchedulesByTime(String time);

    void deleteMovieById(String id);
    void deleteTheaterById(String id);
    void deleteUserById(String id);
    void deleteCommentById(String id);
    void deleteScheduleById(String id);
}
