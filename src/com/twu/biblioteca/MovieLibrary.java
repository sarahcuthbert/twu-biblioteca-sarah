package com.twu.biblioteca;

import java.util.ArrayList;

class MovieLibrary {

    private ArrayList<Movie> availableMovies = new ArrayList<Movie>();
    private boolean viewingMovieList;

    MovieLibrary() {
        this.viewingMovieList = false;
    }

    void displayAllMovies() {
        System.out.println("Available Movies:");
        System.out.printf("%-30.30s %-10.10s %-30.30s %-10.10s%n", "Name", "Year", "Director", "Rating");
        for (Movie movie: availableMovies) {
             movie.printMovie();
        }
    }

    void addMovie(String name, int year, String director, int rating) {
        availableMovies.add(new Movie(name, year, director, rating));
    }

    boolean isViewingMovieList() {
        return viewingMovieList;
    }

    void setViewingMovieList(boolean viewingMovieList) {
        this.viewingMovieList = viewingMovieList;
    }

    void setAvailableMovies(ArrayList<Movie> availableMovies) {
        this.availableMovies = availableMovies;
    }

    ArrayList<Movie> getAvailableMovies() {
        return availableMovies;
    }
}
