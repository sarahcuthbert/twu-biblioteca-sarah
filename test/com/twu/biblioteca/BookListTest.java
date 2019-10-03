package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BookListTest {

    private ByteArrayOutputStream outContent;
    private Library library;

    @Before
    public void setUpStringReader() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        library = new Library(null);
        library.setAvailableBooks(new ArrayList<Book>());
    }

    @Test
    public void testBookListHeadingDisplayed() {
        library.displayAllBooks();
        String expectedList = "Available Books:\n";
        expectedList += "Title                           Author                         Publication Date              \n";
        expectedList += "Type 'login' at any point to login.\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testBookListHeadingDisplayedLoggedIn() {
        library.setCurrentUser(new User("1111-1111", "abc", "name", "email", 1202020));
        library.displayAllBooks();
        String expectedList = "Available Books:\n";
        expectedList += "Title                           Author                         Publication Date              \n";
        expectedList += "Enter 'checkout ' followed by the name of the book to check it out and press Enter.\n";
        expectedList += "Enter 'return ' followed by the name of the book to return and press Enter.\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testAddBookToBookList() {
        library.addBook("Book1", "Author1", 2000);
        assertEquals("Book1", library.getAvailableBooks().get(0).getTitle());
    }

    @Test
    public void testBookListOneDisplayed() {
        library.addBook("Book1", "Author1", 2000);
        library.displayAllBooks();
        String expectedList = "Available Books:\n";
        expectedList += "Title                           Author                         Publication Date              \n";
        expectedList += "Book1                           Author1                        2000                          \n";
        expectedList += "Type 'login' at any point to login.\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testBookListManyDisplayed() {
        library.addBook("Book1", "Author1", 2000);
        library.addBook("Book2", "Author2", 2001);
        library.addBook("Book3", "Author3", 2003);
        library.displayAllBooks();
        String expectedList = "Available Books:\n";
        expectedList += "Title                           Author                         Publication Date              \n";
        expectedList += "Book1                           Author1                        2000                          \n";
        expectedList += "Book2                           Author2                        2001                          \n";
        expectedList += "Book3                           Author3                        2003                          \n";
        expectedList += "Type 'login' at any point to login.\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testBookListLongNameDisplayed() {
        library.addBook("Book1", "Author1", 2000);
        library.addBook("Book1Book2Book3Book4Book5", "Author2", 2001);
        library.displayAllBooks();
        String expectedList = "Available Books:\n";
        expectedList += "Title                           Author                         Publication Date              \n";
        expectedList += "Book1                           Author1                        2000                          \n";
        expectedList += "Book1Book2Book3Book4Book5       Author2                        2001                          \n";
        expectedList += "Type 'login' at any point to login.\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testCheckOutBook() {
        library.setCurrentUser(new User("1111-1111", "abc", "name", "email", 1202020));
        library.addBook("Book1", "Author1", 2000);
        library.checkOutBook("Book1");
        assertEquals(0, library.getAvailableBooks().size());
    }

    @Test
    public void testCheckOutBookConfirmMessage() {
        library.setCurrentUser(new User("1111-1111", "abc", "name", "email", 1202020));
        library.addBook("Book1", "Author1", 2000);
        library.checkOutBook("Book1");
        String expectedList = "Thank you! Enjoy the book.\n";
        expectedList += "Available Books:\n";
        expectedList += "Title                           Author                         Publication Date              \n";
        expectedList += "Enter 'checkout ' followed by the name of the book to check it out and press Enter.\n";
        expectedList += "Enter 'return ' followed by the name of the book to return and press Enter.\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testCheckOutBookErrorMessage() {
        library.addBook("Book1", "Author1", 2000);
        library.checkOutBook("Book2");
        String expectedList = "Sorry, that book is not available\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testReturnBook() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Book1", "Author1", 2000) );
        library.setCheckedOutBooks(books);
        library.returnBook("Book1");
        assertEquals(1, library.getAvailableBooks().size());
    }

    @Test
    public void testReturnBookConfirmMessage() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Book1", "Author1", 2000) );
        library.setCheckedOutBooks(books);
        library.returnBook("book1");
        String expectedList = "Thank you for returning the book.\n";
        expectedList += "Available Books:\n";
        expectedList += "Title                           Author                         Publication Date              \n";
        expectedList += "Book1                           Author1                        2000                          \n";
        expectedList += "Type 'login' at any point to login.\n";
        assertEquals(expectedList, outContent.toString());
    }

    @Test
    public void testReturnBookErrorMessage() {
        library.returnBook("book1");
        String expectedList = "That is not a valid book to return.\n";
        assertEquals(expectedList, outContent.toString());
    }

}
