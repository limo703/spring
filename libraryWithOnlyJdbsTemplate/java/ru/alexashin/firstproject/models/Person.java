package ru.alexashin.firstproject.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Person {
    private int id;
    @NotNull(message = "Name cannot be empty")
    private String name;
    @Min(value = 0, message = "Age cannot be ")
    private int age;

    public Person(int id, String name, int age) {
        this.id = id;
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
