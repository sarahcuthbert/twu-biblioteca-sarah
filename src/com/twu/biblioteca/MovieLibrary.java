package com.twu.biblioteca;

import java.util.ArrayList;

class MovieLibrary {
    private static final String CHECKOUT_MESSAGE = "Enter 'checkout ' followed by the name of the movie to check it out and press Enter.";
    private static final String CHECKOUT_CONFIRM_MESSAGE = "Thank you! Enjoy the movie.";
    private static final String CHECKOUT_ERROR_MESSAGE = "Sorry, that movie is not available";
    private static final String LOGIN_MESSAGE = "Type 'login' at any point to login.";

    private ArrayList<Movie> availableMovies = new ArrayList<Movie>();
    private boolean viewingMovieList;
    private User currentUser;

    MovieLibrary(User currentUser) {
        this.viewingMovieList = false;
        this.currentUser = currentUser;
    }

    void displayAllMovies() {
        System.out.println("Available Movies:");
        System.out.printf("%-30.30s %-10.10s %-30.30s %-10.10s%n", "Name", "Year", "Director", "Rating");
        for (Movie movie: availableMovies) {
             movie.printMovie();
        }
        if (currentUser != null) System.out.println(CHECKOUT_MESSAGE);
        if(currentUser == null) System.out.println(LOGIN_MESSAGE);

    }

    void checkOutMovie(String movieName) {
        int position = findMovie(movieName);
        if (position > -1) {
            Movie movie = availableMovies.remove(position);
            currentUser.checkoutMovie(movie);
            System.out.println(CHECKOUT_CONFIRM_MESSAGE);
            displayAllMovies();
        }
        else {
            System.out.println(CHECKOUT_ERROR_MESSAGE);
        }
    }

    private int findMovie(String movieName) {
        movieName = movieName.toLowerCase();
        for (int i = 0; i < availableMovies.size(); i++) {
            if (availableMovies.get(i).getName().toLowerCase().equals(movieName)) {
                return i;
            }
        }
        return -1;
    }

    void handleMovieListInput(String request) {
        String[] requestArray = request.split(" ", 2);
        System.out.println(requestArray[0]);
        if (requestArray[0].equals("checkout")) {
            checkOutMovie(requestArray[1]);
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

    void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
