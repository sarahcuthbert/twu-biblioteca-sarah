package com.twu.biblioteca;

import java.util.ArrayList;

public class BibliotecaApp {
    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static ArrayList<String> books = new ArrayList<String>();

    public static void main(String[] args) {
        printWelcomeMessage();
        displayAllBooks();
    }

    static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    static void displayAllBooks() {
        System.out.println("Available Books:");
        for (String book: books
             ) {
            System.out.println(book);
        }
    }

    static void addBook(String bookName) {
        books.add(bookName);
    }

    static ArrayList<String> getBooks() {
        return books;
    }
}
