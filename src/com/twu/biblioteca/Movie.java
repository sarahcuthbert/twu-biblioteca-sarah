package com.twu.biblioteca;

class Movie {

    private String name;
    private int year;
    private String director;
    private int rating;

    Movie(String name, int year, String director, int rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    void printMovie() {
        String movieRating = "unrated";
        if(rating <=10 && rating >= 1) movieRating = Integer.toString(rating);
        System.out.printf("%-30.30s %-10.10s %-30.30s %-10.10s%n", name, year, director, movieRating);
    }

    String getName() {
        return name;
    }
}
