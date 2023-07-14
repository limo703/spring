package ru.alexashin.firstproject.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Book {
    private int id;
    @NotNull(message = "Book name cannot be empty")
    private String nameOfBook;
    @NotNull(message = "Creator name cannot be empty")
    private String nameOfCreator;
    @Min(value = 0,message = "Year cannot be lower than 0")
    private int year;
    private int idOfOwner;
    public Book(){}



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfBook() {
        return nameOfBook;
    }

    public void setNameOfBook(String nameOfBook) {
        this.nameOfBook = nameOfBook;
    }

    public String getNameOfCreator() {
        return nameOfCreator;
    }

    public void setNameOfCreator(String nameOfCreator) {
        this.nameOfCreator = nameOfCreator;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Book(int id, String nameOfBook, String nameOfCreator, int year) {
        this.id = id;
        this.nameOfBook = nameOfBook;
        this.nameOfCreator = nameOfCreator;
        this.year = year;
    }
}
