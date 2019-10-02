package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {
    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String MENU_MESSAGE = "MAIN MENU. \nEnter the number of what you would like to see and press Enter.";
    private static final String ERROR_INPUT_MESSAGE = "Incorrect Input. Please enter a valid number.";
    private static ArrayList<Book> books = new ArrayList<Book>();

    public static void main(String[] args) {
        printWelcomeMessage();
        displayMainMenu();
        getUserSelection();
    }

    static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    static void displayMainMenu() {
        System.out.println(MENU_MESSAGE);
        System.out.println("1. List of Books");
    }

    private static void getUserSelection() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userIn = scanner.nextLine();
            if (handleUserResponse(userIn)) break;
        }
    }

    static boolean handleUserResponse(String userIn) {
        try {
            int response = Integer.parseInt(userIn);
            if (response == 1) {
                displayAllBooks();
                return true;
            }
            else {
                System.out.println(ERROR_INPUT_MESSAGE);
            }
        } catch (NumberFormatException nfe) {
            System.out.println(ERROR_INPUT_MESSAGE);
        }
        return false;
    }

    static void displayAllBooks() {
        System.out.println("Available Books:");
        System.out.printf("%-30.30s  %-30.30s %-30.30s%n", "Title", "Author", "Publication Date");
        for (Book book: books) {
                book.printBook();
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
