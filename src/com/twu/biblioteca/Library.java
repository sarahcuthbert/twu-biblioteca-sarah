package com.twu.biblioteca;

import java.util.ArrayList;

class Library {

    private static final String CHECKOUT_MESSAGE = "Enter 'checkout ' followed by the name of the book to check it out and press Enter.";
    private static final String CHECKOUT_CONFIRM_MESSAGE = "Thank you! Enjoy the book.";
    private static final String CHECKOUT_ERROR_MESSAGE = "Sorry, that book is not available";
    private static final String RETURN_MESSAGE = "Enter 'return ' followed by the name of the book to return and press Enter.";
    private static final String RETURN_SUCCESS_MESSAGE = "Thank you for returning the book.";
    private static final String RETURN_ERROR_MESSAGE = "That is not a valid book to return.";

    private boolean viewingBookList;

    private ArrayList<Book> availableBooks = new ArrayList<Book>();
    private ArrayList<Book> checkedOutBooks = new ArrayList<Book>();

    Library() {
        this.viewingBookList = false;
    }

    void displayAllBooks() {
        System.out.println("Available Books:");
        System.out.printf("%-30.30s  %-30.30s %-30.30s%n", "Title", "Author", "Publication Date");
        for (Book book: availableBooks) {
            book.printBook();
        }
        System.out.println(CHECKOUT_MESSAGE);
        System.out.println(RETURN_MESSAGE);
    }


    void checkOutBook(String bookName) {
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

    void returnBook(String bookName) {
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

    private int findBook(String bookName, ArrayList<Book> books) {
        bookName = bookName.toLowerCase();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().toLowerCase().equals(bookName)) {
                return i;
            }
        }
        return -1;
    }

    void handleBookListInput(String request) {
        String[] requestArray = request.split(" ");
        String bookName = request.split(" ", 2)[1];
        if (requestArray[0].equals("checkout")) {
            checkOutBook(bookName);
        }
        else if (requestArray[0].equals("return")) {
            returnBook(bookName);
        }
    }

    void addBook(String bookName, String author, int publicationDate) {
        availableBooks.add(new Book(bookName, author, publicationDate));
    }

    ArrayList<Book> getAvailableBooks() {
        return availableBooks;
    }

    void setAvailableBooks(ArrayList<Book> availableBooks) {
        this.availableBooks = availableBooks;
    }

    void setCheckedOutBooks(ArrayList<Book> checkedOutBooks) {
        this.checkedOutBooks = checkedOutBooks;
    }

    boolean isViewingBookList() {
        return viewingBookList;
    }

    void setViewingBookList(boolean viewingBookList) {
        this.viewingBookList = viewingBookList;
    }
}
