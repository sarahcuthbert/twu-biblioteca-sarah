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
        setAvailableBooks(new ArrayList<Book>());
    }

    @Test
    public void testBookListHeadingDisplayed() {
        displayAllBooks();
        String expectedList = "Available Books:\n";
        expectedList += "Title                           Author                         Publication Date              \n";
        expectedList += "Enter 'checkout ' followed by the name of the book to check it out and press Enter.\n";
        expectedList += "Enter 'return ' followed by the name of the book to return and press Enter.\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testAddBookToBookList() {
        addBook("Book1", "Author1", 2000);
        assertEquals("Book1", getAvailableBooks().get(0).getTitle());
    }

    @Test
    public void testBookListOneDisplayed() {
        addBook("Book1", "Author1", 2000);
        displayAllBooks();
        String expectedList = "Available Books:\n";
        expectedList += "Title                           Author                         Publication Date              \n";
        expectedList += "Book1                           Author1                        2000                          \n";
        expectedList += "Enter 'checkout ' followed by the name of the book to check it out and press Enter.\n";
        expectedList += "Enter 'return ' followed by the name of the book to return and press Enter.\n";
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
        expectedList += "Enter 'checkout ' followed by the name of the book to check it out and press Enter.\n";
        expectedList += "Enter 'return ' followed by the name of the book to return and press Enter.\n";
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
        expectedList += "Enter 'checkout ' followed by the name of the book to check it out and press Enter.\n";
        expectedList += "Enter 'return ' followed by the name of the book to return and press Enter.\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testCheckOutBook() {
        addBook("Book1", "Author1", 2000);
        checkOutBook("Book1");
        assertEquals(0, getAvailableBooks().size());
    }

    @Test
    public void testCheckOutBookConfirmMessage() {
        addBook("Book1", "Author1", 2000);
        checkOutBook("Book1");
        String expectedList = "Thank you! Enjoy the book.\n";
        expectedList += "Available Books:\n";
        expectedList += "Title                           Author                         Publication Date              \n";
        expectedList += "Enter 'checkout ' followed by the name of the book to check it out and press Enter.\n";
        expectedList += "Enter 'return ' followed by the name of the book to return and press Enter.\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testCheckOutBookErrorMessage() {
        addBook("Book1", "Author1", 2000);
        checkOutBook("Book2");
        String expectedList = "Sorry, that book is not available\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testReturnBook() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Book1", "Author1", 2000) );
        setCheckedOutBooks(books);
        returnBook("Book1");
        assertEquals(1, getAvailableBooks().size());
    }

    @Test
    public void testReturnBookConfirmMessage() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Book1", "Author1", 2000) );
        setCheckedOutBooks(books);
        returnBook("book1");
        String expectedList = "Thank you for returning the book.\n";
        expectedList += "Available Books:\n";
        expectedList += "Title                           Author                         Publication Date              \n";
        expectedList += "Book1                           Author1                        2000                          \n";
        expectedList += "Enter 'checkout ' followed by the name of the book to check it out and press Enter.\n";
        expectedList += "Enter 'return ' followed by the name of the book to return and press Enter.\n";
        assertEquals(expectedList, outContent.toString());
    }

}
