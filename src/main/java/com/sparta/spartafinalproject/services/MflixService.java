package com.sparta.spartafinalproject.services;

import com.sparta.spartafinalproject.documents.*;

import java.util.List;

public interface MflixService {

    boolean createMovie(Movie mov);
    boolean createTheater(Theater th);
    boolean createUser(User user);
    boolean createComment(Comment com);
    boolean createSchedule(Schedule sch);

    List<Movie> getAllMovies();
    Movie getMovieById(String movieId);
    Movie getMovieBySchedule(Schedule s);

    List<Theater> getAllTheaters();
    Theater getTheaterById(String id);
    Theater getTheaterByTheaterId(int id);

    Theater getTheaterBySchedule(Schedule s);
    List<User> getAllUsers();
    User getUserByEmail(String email);

    User getUserById(String id);
    List<Comment> getAllComments();
    List<Comment> getCommentsByMovie(Movie movie);
    List<Comment> getCommentsByMovieId(String movieId);
    Comment getCommentById(String id);

    List<Schedule> getAllSchedules();
    Schedule getScheduleById(String id);

    List<Schedule> getSchedulesByMovie(Movie movie);
    List<Schedule> getSchedulesByMovieId(String movieId);
    List<Schedule> getSchedulesByTime(String time);
    List<Schedule> getSchedulesByTheater(Theater th);
    /**
     *
     * @return true if update was successful
     */
    boolean updateMovie(Movie mov);
    /**
     *
     * @return true if update was successful
     */
    boolean updateTheater(Theater th);
    /**
     *
     * @return true if update was successful
     */
    boolean updateUser(User user);
    /**
     *
     * @return true if update was successful
     */
    boolean updateComment(Comment com);

    /**
     *
     * @return true if update was successful
     */
    boolean updateSchedule(Schedule sch);


    boolean deleteMovieById(String id);
    boolean deleteTheaterById(String id);
    boolean deleteUserById(String id);
    boolean deleteCommentById(String id);
    boolean deleteScheduleById(String id);
}
