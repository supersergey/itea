package com.example.demo.controller.dto;

public class User {
    private final String name;
    private final String lastName;

    public User(String name, String lastName)
    {
        this.name = name;
        this.lastName =lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public  String getName()
    {
        return  name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}