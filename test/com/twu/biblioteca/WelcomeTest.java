package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.twu.biblioteca.BibliotecaApp.printWelcomeMessage;
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
}
