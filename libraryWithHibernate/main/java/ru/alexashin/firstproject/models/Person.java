package ru.alexashin.firstproject.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    @Size(max = 100, message = "Name of book can be under 100 symbols")
    @NotNull(message = "Name cannot be empty")
    private String name;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Column(name = "age")
    @Min(value = 0, message = "Age cannot be lower than 0")
    private int age;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Person(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
