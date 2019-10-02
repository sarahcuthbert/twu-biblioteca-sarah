package com.twu.biblioteca;

class Book {

    private String title;
    private String author;
    private int publicationYear;

    Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    void printBook() {
        System.out.printf("%-30.30s  %-30.30s %-30.30s%n", title, author, publicationYear);
    }

    String getTitle() {
        return title;
    }
}
