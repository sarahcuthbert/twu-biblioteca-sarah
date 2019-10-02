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

    void printBook(int i) {
        System.out.printf("%-10.10s %-30.30s  %-30.30s %-30.30s%n", i, title, author, publicationYear);
    }

    String getTitle() {
        return title;
    }
}
