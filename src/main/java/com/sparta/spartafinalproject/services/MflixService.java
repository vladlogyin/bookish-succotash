package com.sparta.spartafinalproject.services;

import com.sparta.spartafinalproject.documents.Comment;
import com.sparta.spartafinalproject.documents.Movie;
import com.sparta.spartafinalproject.documents.Theater;
import com.sparta.spartafinalproject.documents.User;

import java.util.List;

public interface MflixService {
    List<Movie> getAllMovies();
    List<Theater> getAllTheaters();
    List<User> getAllUsers();
    List<Comment> getAllComments();
    List<Comment> getCommentsByMovie(Movie movie);
    Comment getCommentById(String id);
    void postComment(Comment comment);
}
