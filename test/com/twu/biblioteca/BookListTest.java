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
        expectedList += "Number     Title                           Author                         Publication Date              \n";
        expectedList += "Enter the number of a book to check it out and press Enter.\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testAddBookToBookList() {
        addBook("Book1", "Author1", 2000);
        assertEquals("Book1", getBooks().get(0).getTitle());
    }

    @Test
    public void testBookListOneDisplayed() {
        addBook("Book1", "Author1", 2000);
        displayAllBooks();
        String expectedList = "Available Books:\n";
        expectedList += "Number     Title                           Author                         Publication Date              \n";
        expectedList += "1          Book1                           Author1                        2000                          \n";
        expectedList += "Enter the number of a book to check it out and press Enter.\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testBookListManyDisplayed() {
        addBook("Book1", "Author1", 2000);
        addBook("Book2", "Author2", 2001);
        addBook("Book3", "Author3", 2003);
        displayAllBooks();
        String expectedList = "Available Books:\n";
        expectedList += "Number     Title                           Author                         Publication Date              \n";
        expectedList += "1          Book1                           Author1                        2000                          \n";
        expectedList += "2          Book2                           Author2                        2001                          \n";
        expectedList += "3          Book3                           Author3                        2003                          \n";
        expectedList += "Enter the number of a book to check it out and press Enter.\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testBookListLongNameDisplayed() {
        addBook("Book1", "Author1", 2000);
        addBook("Book1Book2Book3Book4Book5", "Author2", 2001);
        displayAllBooks();
        String expectedList = "Available Books:\n";
        expectedList += "Number     Title                           Author                         Publication Date              \n";
        expectedList += "1          Book1                           Author1                        2000                          \n";
        expectedList += "2          Book1Book2Book3Book4Book5       Author2                        2001                          \n";
        expectedList += "Enter the number of a book to check it out and press Enter.\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testCheckOutBook() {
        addBook("Book1", "Author1", 2000);
        checkOutBook(1);
        assertEquals(0, getBooks().size());
    }
}
