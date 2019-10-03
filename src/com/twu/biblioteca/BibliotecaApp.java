package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {
    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String MENU_MESSAGE = "MAIN MENU. \nEnter the number of what you would like to see and press Enter.";
    private static final String ERROR_INPUT_MESSAGE = "Please select a valid option!";

    private static Library library;
    private static MovieLibrary movieLibrary;
    private static Scanner scanner;
    private static ArrayList<User> userList;
    private static User currentUser;

    public static void main(String[] args) {
        library = new Library(currentUser);
        movieLibrary = new MovieLibrary(currentUser);
        userList = new ArrayList<User>();
        addUser("1111-1111", "abc", "name", "email", 1202020);
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
        System.out.println("2. List of Movies");
        if(currentUser != null) System.out.println("3. View My Information");
        System.out.println("Type 'exit' at any point to quit.");
        if(currentUser == null) System.out.println("Type 'login' at any point to login.");

    }

    private static void getUserSelection() {
        scanner = new Scanner(System.in);
        while (true) {
            String userIn = scanner.nextLine();
            if(!handleUserResponse(userIn)) return;
        }
    }

    static boolean handleUserResponse(String userIn) {

            if (userIn.toLowerCase().equals("exit")) return false;
            if (userIn.toLowerCase().equals("login")) {
                getUser();
            }
            else if (library.isViewingBookList() && currentUser != null) {
                String request = userIn.toLowerCase();
                library.handleBookListInput(request);
            }
            else if(movieLibrary.isViewingMovieList() && currentUser != null) {
                String request = userIn.toLowerCase();
                movieLibrary.handleMovieListInput(request);
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
        if (response == 1 ) {
            library.displayAllBooks();
            library.setViewingBookList(true);
        }
        else if (response == 2) {
            movieLibrary.displayAllMovies();
            movieLibrary.setViewingMovieList(true);
        }
        else if (currentUser != null && response == 3) {
            currentUser.displayInformation();
        }
        else {
            System.out.println(ERROR_INPUT_MESSAGE);
        }
    }

    private static void getUser() {
        while (true) {
            System.out.println("Library Number:");
            String libNumber = scanner.nextLine();
            String pattern = "\\d\\d\\d\\d-\\d\\d\\d\\d";
            if (libNumber.matches(pattern)) {
                System.out.println("Password:");
                String password = scanner.nextLine();
                User user = findUser(libNumber, password);
                if(user == null) {
                    System.out.println("Incorrect credentials. Try again.");
                }
                else {
                    currentUser = user;
                    movieLibrary.setCurrentUser(user);
                    System.out.println("Successfully logged in!");
                    break;
                }
            }
            else {
                System.out.println("Incorrect format. Please try again.");
            }
        }
    }

    private static User findUser(String libNumber, String password) {
        for (User u: userList) {
            if (u.getLibNumber().equals(libNumber) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    private static void addUser(String libNumber, String password, String name, String email, int phone) {
        userList.add(new User(libNumber, password, name, email, phone));
    }

    static void setLibrary(Library library) {
        BibliotecaApp.library = library;
    }

    static void setMovieLibrary(MovieLibrary movieLibrary) {
        BibliotecaApp.movieLibrary = movieLibrary;
    }

    static void setCurrentUser(User currentUser) {
        BibliotecaApp.currentUser = currentUser;
    }
}
