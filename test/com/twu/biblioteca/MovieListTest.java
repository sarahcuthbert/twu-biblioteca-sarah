package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MovieListTest {

    private ByteArrayOutputStream outContent;
    private MovieLibrary library;

    @Before
    public void setUpStringReader() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        library = new MovieLibrary();
        library.setAvailableMovies(new ArrayList<Movie>());
    }

    @Test
    public void testMovieListHeadingDisplayed() {
        library.displayAllMovies();
        String expectedList = "Available Movies:\n";
        expectedList += "Name                           Year       Director                       Rating    \n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testAddMovieToMovieList() {
        library.addMovie("movie1", 2001, "director1", 5);
        assertEquals(1, library.getAvailableMovies().size());
    }

    @Test
    public void testMovieListOneDisplayed() {
        library.addMovie("movie1", 2001, "director1", 5);
        library.displayAllMovies();
        String expectedList = "Available Movies:\n";
        expectedList += "Name                           Year       Director                       Rating    \n";
        expectedList += "movie1                         2001       director1                      5         \n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testMovieListManyDisplayed() {
        library.addMovie("movie1", 2001, "director1", 1);
        library.addMovie("movie2", 2002, "director2", 8);
        library.addMovie("movie3", 2003, "director1", 10);
        library.displayAllMovies();
        String expectedList = "Available Movies:\n";
        expectedList += "Name                           Year       Director                       Rating    \n";
        expectedList += "movie1                         2001       director1                      1         \n";
        expectedList += "movie2                         2002       director2                      8         \n";
        expectedList += "movie3                         2003       director1                      10        \n";
        assertEquals(expectedList, outContent.toString());
    }

}
