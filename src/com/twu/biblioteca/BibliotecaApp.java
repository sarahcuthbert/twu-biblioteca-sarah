package com.twu.biblioteca;

import java.util.ArrayList;

public class BibliotecaApp {
    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static ArrayList<Book> books = new ArrayList<Book>();

    public static void main(String[] args) {
        printWelcomeMessage();
        displayAllBooks();
    }

    static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    static void displayAllBooks() {
        System.out.println("Available Books:");
        System.out.printf("%-30.30s  %-30.30s %-30.30s%n", "Title", "Author", "Publication Date");
        for (Book book: books
             ) {
            System.out.printf("%-30.30s  %-30.30s %-30.30s%n", book.title, book.author, book.publicationYear);
        }
    }

    static void addBook(String bookName, String author, int publicationDate) {
        books.add(new Book(bookName, author, publicationDate));
    }

    static ArrayList<Book> getBooks() {
        return books;
    }

    static void setBooks(ArrayList<Book> books) {
        BibliotecaApp.books = books;
    }
}
