package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BibliotecaApp {
    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String MENU_MESSAGE = "MAIN MENU. \nEnter the number of what you would like to see and press Enter.";
    private static final String ERROR_INPUT_MESSAGE = "Please select a valid option!";
    private static final String CHECKOUT_MESSAGE = "Enter 'checkout ' followed by the name of the book to check it out and press Enter.";
    private static final String CHECKOUT_CONFIRM_MESSAGE = "Thank you! Enjoy the book.";
    private static final String CHECKOUT_ERROR_MESSAGE = "Sorry, that book is not available";
    private static final String RETURN_MESSAGE = "Enter 'return ' followed by the name of the book to return and press Enter.";
    private static final String RETURN_SUCCESS_MESSAGE = "Thank you for returning the book.";
    private static final String RETURN_ERROR_MESSAGE = "That is not a valid book to return.";

    private static ArrayList<Book> availableBooks = new ArrayList<Book>();
    private static ArrayList<Book> checkedOutBooks = new ArrayList<Book>();
    private static boolean viewingBookList = false;

    public static void main(String[] args) {
        printWelcomeMessage();
        addBook("Book1", "Author1", 2000);
        addBook("Book1Book2Book3Book4Book5", "Author2", 2001);
        displayMainMenu();
        getUserSelection();
    }

    static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    static void displayMainMenu() {
        System.out.println(MENU_MESSAGE);
        System.out.println("1. List of Books");
        System.out.println("Type 'exit' at any point to quit.");
    }

    private static void getUserSelection() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userIn = scanner.nextLine();
            if(!handleUserResponse(userIn)) return;
        }
    }

    static boolean handleUserResponse(String userIn) {
        try {
            if (userIn.toLowerCase().equals("exit")) return false;
            if (viewingBookList) {
                String request = userIn.toLowerCase();
                String[] requestArray = request.split(" ");
                if (requestArray[0].equals("checkout")) {
                    String bookName = request.split(" ", 2)[1];
                    System.out.println(bookName);
                    checkOutBook(bookName);
                }
                else if (requestArray[0].equals("return")) {
                    String bookName = request.split(" ", 2)[1];
                    returnBook(bookName);
                }

            }
            else {
                int response = Integer.parseInt(userIn);
                if (response == 1 && !viewingBookList) {
                    displayAllBooks();
                    viewingBookList = true;
                } else {
                    System.out.println(ERROR_INPUT_MESSAGE);
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println(ERROR_INPUT_MESSAGE);
        }
        return true;
    }

    static void displayAllBooks() {
        System.out.println("Available Books:");
        System.out.printf("%-30.30s  %-30.30s %-30.30s%n", "Title", "Author", "Publication Date");
        for (Book book: availableBooks) {
                book.printBook();
        }
        System.out.println(CHECKOUT_MESSAGE);
        System.out.println(RETURN_MESSAGE);
    }

    static void checkOutBook(String bookName) {
        int bookPosition = findBook(bookName, availableBooks);
        if (bookPosition > -1) {
            Book book = availableBooks.remove(bookPosition);
            checkedOutBooks.add(book);
            System.out.println(CHECKOUT_CONFIRM_MESSAGE);
            displayAllBooks();
        }
        else {
            System.out.println(CHECKOUT_ERROR_MESSAGE);
        }
    }

    static void returnBook(String bookName) {
        int bookPosition = findBook(bookName, checkedOutBooks);
        if (bookPosition > -1) {
            Book book = checkedOutBooks.remove(bookPosition);
            availableBooks.add(book);
            System.out.println(RETURN_SUCCESS_MESSAGE);
            displayAllBooks();
        }
        else {
            System.out.println(RETURN_ERROR_MESSAGE);

        }
    }

    private static int findBook(String bookName, ArrayList<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().toLowerCase().equals(bookName)) {
                return i;
            }
        }
        return -1;
    }

    static void addBook(String bookName, String author, int publicationDate) {
        availableBooks.add(new Book(bookName, author, publicationDate));
    }

    static ArrayList<Book> getAvailableBooks() {
        return availableBooks;
    }

    static void setAvailableBooks(ArrayList<Book> availableBooks) {
        BibliotecaApp.availableBooks = availableBooks;
    }

    static void setCheckedOutBooks(ArrayList<Book> checkedOutBooks) {
        BibliotecaApp.checkedOutBooks = checkedOutBooks;
    }
}
