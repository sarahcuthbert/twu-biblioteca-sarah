package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {
    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String MENU_MESSAGE = "MAIN MENU. \nEnter the number of what you would like to see and press Enter.";
    private static final String ERROR_INPUT_MESSAGE = "Please select a valid option!";

    private static boolean viewingBookList = false;
    private static Library library;

    public static void main(String[] args) {
        library = new Library();
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

            if (userIn.toLowerCase().equals("exit")) return false;
            if (viewingBookList) {
                String request = userIn.toLowerCase();
                library.handleBookListInput(request);
            }
            else {
                try {
                    int response = Integer.parseInt(userIn);
                    handleUserMainMenuInput(response);
                }
                catch (NumberFormatException nfe) {
                    System.out.println(ERROR_INPUT_MESSAGE);
                }
            }
        return true;
    }

    private static void handleUserMainMenuInput(int response) {
        if (response == 1 && !viewingBookList) {
            library.displayAllBooks();
            viewingBookList = true;
        } else {
            System.out.println(ERROR_INPUT_MESSAGE);
        }
    }

    static void setLibrary(Library library) {
        BibliotecaApp.library = library;
    }
}
