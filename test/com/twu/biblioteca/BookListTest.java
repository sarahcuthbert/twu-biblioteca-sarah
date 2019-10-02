package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.twu.biblioteca.BibliotecaApp.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BookListTest {

    private ByteArrayOutputStream outContent;

    @Before
    public void setUpStringReader() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testBookListHeadingDisplayed() {
        displayAllBooks();
        String expectedList = "Available Books:\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testAddBookToBookList() {
        addBook("Book1");
        assertEquals("Book1", getBooks().get(0));
    }

    @Test
    public void testBookListOneDisplayed() {
        addBook("Book1");
        displayAllBooks();
        String expectedList = "Available Books:\nBook1\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testBookListManyDisplayed() {
        addBook("Book1");
        addBook("Book2");
        addBook("Book3");
        displayAllBooks();
        String expectedList = "Available Books:\nBook1\nBook2\nBook3\n";
        assertEquals(expectedList, outContent.toString());
    }

}
