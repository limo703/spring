package ru.alexashin.firstproject.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max = 100, message = "Name of book can be under 100 symbols")
    @NotNull(message = "Book name cannot be empty")
    @Column(name = "nameOfBook")
    private String nameOfBook;

    @Size(max = 100, message = "Name of book can be under 100 symbols")
    @NotNull(message = "Creator name cannot be empty")
    @Column(name = "nameOfCreator")
    private String nameOfCreator;

    @Column(name = "year")
    @Min(value = 0,message = "Year cannot be lower than 0")
    private int year;

    @Column(name="taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @ManyToOne
    @JoinColumn(name="idOfOwner", referencedColumnName = "id")
    private Person owner;

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Transient
    private boolean expired;

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }


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
        this.nameOfBook = nameOfBook;
        this.nameOfCreator = nameOfCreator;
        this.year = year;
    }
}
