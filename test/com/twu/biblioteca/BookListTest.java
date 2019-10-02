package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static com.twu.biblioteca.BibliotecaApp.*;
import static org.junit.Assert.assertEquals;

public class BookListTest {

    private ByteArrayOutputStream outContent;

    @Before
    public void setUpStringReader() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        setBooks(new ArrayList<Book>());
    }

    @Test
    public void testBookListHeadingDisplayed() {
        displayAllBooks();
        String expectedList = "Available Books:\n";
        expectedList += "Title                           Author                         Publication Date              \n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testAddBookToBookList() {
        addBook("Book1", "Author1", 2000);
        assertEquals("Book1", getBooks().get(0).title);
    }

    @Test
    public void testBookListOneDisplayed() {
        addBook("Book1", "Author1", 2000);
        displayAllBooks();
        String expectedList = "Available Books:\n";
        expectedList += "Title                           Author                         Publication Date              \n";
        expectedList += "Book1                           Author1                        2000                          \n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testBookListManyDisplayed() {
        addBook("Book1", "Author1", 2000);
        addBook("Book2", "Author2", 2001);
        addBook("Book3", "Author3", 2003);
        displayAllBooks();
        String expectedList = "Available Books:\n";
        expectedList += "Title                           Author                         Publication Date              \n";
        expectedList += "Book1                           Author1                        2000                          \n";
        expectedList += "Book2                           Author2                        2001                          \n";
        expectedList += "Book3                           Author3                        2003                          \n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testBookListLongNameDisplayed() {
        addBook("Book1", "Author1", 2000);
        addBook("Book1Book2Book3Book4Book5", "Author2", 2001);
        displayAllBooks();
        String expectedList = "Available Books:\n";
        expectedList += "Title                           Author                         Publication Date              \n";
        expectedList += "Book1                           Author1                        2000                          \n";
        expectedList += "Book1Book2Book3Book4Book5       Author2                        2001                          \n";
        assertEquals(expectedList, outContent.toString());
    }
}
