package com.twu.biblioteca;

import java.util.ArrayList;

class User {

    private String libNumber;
    private String password;
    private ArrayList<Book> checkedBooks;
    private ArrayList<Movie> checkedMovies;

    User(String libNumber, String password) {
        this.libNumber = libNumber;
        this.password = password;
        checkedBooks = new ArrayList<Book>();
        checkedMovies = new ArrayList<Movie>();
    }

    String getLibNumber() {
        return libNumber;
    }

    String getPassword() {
        return password;
    }

    void checkoutBook(Book book) {
        checkedBooks.add(book);
    }

    void checkoutMovie(Movie movie) {
        checkedMovies.add(movie);
    }

}
