package com.twu.biblioteca;

import java.util.ArrayList;

class User {

    private String libNumber;
    private String password;
    private String name;
    private String email;
    private int phoneNumber;

    private ArrayList<Book> checkedBooks;
    private ArrayList<Movie> checkedMovies;

    User(String libNumber, String password, String name, String email, int phoneNumber) {
        this.libNumber = libNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    void displayInformation() {
        System.out.println("Your Information:\n");
        System.out.println("Library Number: " + libNumber);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
    }

}
