package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.twu.biblioteca.BibliotecaApp.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class WelcomeTest {

    private ByteArrayOutputStream outContent;

    @Before
    public void setUpStringReader() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testWelcomeMessageDisplayed() {
        printWelcomeMessage();
        assertFalse(outContent.toString().isEmpty());
    }

    @Test
    public void testWelcomeMessageCorrectTextDisplayed() {
        printWelcomeMessage();
        String expectedWelcomeMsg = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";
        assertEquals(expectedWelcomeMsg, outContent.toString());
    }

    @Test
    public void testMenuTextDisplayed() {
        displayMainMenu();
        String expectedMenuMsg = "MAIN MENU. \nEnter the number of what you would like to see and press Enter.\n";
        expectedMenuMsg += "1. List of Books\n";
        expectedMenuMsg += "2. List of Movies\n";
        expectedMenuMsg += "Type 'exit' at any point to quit.\n";
        assertEquals(expectedMenuMsg, outContent.toString());
    }

    @Test
    public void testMenuResponseExpected() {
        setLibrary(new Library());
        setMovieLibrary(new MovieLibrary());
        handleUserResponse("1");
        String expectedList = "Available Books:\n";
        expectedList += "Title                           Author                         Publication Date              \n";
        expectedList += "Enter 'checkout ' followed by the name of the book to check it out and press Enter.\n";
        expectedList += "Enter 'return ' followed by the name of the book to return and press Enter.\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testMenuResponseExpectedToMovies() {
        setMovieLibrary(new MovieLibrary());
        setLibrary(new Library());
        handleUserResponse("2");
        String expectedList = "Available Movies:\n";
        expectedList += "Name                           Year       Director                       Rating    \n";
        expectedList += "Enter 'checkout ' followed by the name of the movie to check it out and press Enter.\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testMenuResponseUnexpected() {
        setMovieLibrary(new MovieLibrary());
        setLibrary(new Library());
        handleUserResponse("abc");
        String expectedError = "Please select a valid option!\n";
        assertEquals(expectedError, outContent.toString());
    }

    @Test
    public void testMenuResponseNumberBig() {
        setMovieLibrary(new MovieLibrary());
        setLibrary(new Library());
        handleUserResponse("100");
        String expectedError = "Please select a valid option!\n";
        assertEquals(expectedError, outContent.toString());
    }

    @Test
    public void testMenuResponseExit() {
        boolean handle = handleUserResponse("exit");
        assertFalse(handle);
    }

    @Test
    public void testMenuResponseExitCaps() {
        boolean handle = handleUserResponse("EXIt");
        assertFalse(handle);
    }

}
