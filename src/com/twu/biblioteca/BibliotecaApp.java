package com.twu.biblioteca;

public class BibliotecaApp {
    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    public static void main(String[] args) {
        printWelcomeMessage();
    }

    static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }
}
