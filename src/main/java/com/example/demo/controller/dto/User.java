package com.example.demo.controller.dto;

public class User {
    private final String name;
    private final String lastName;
    private final int age;

    public User(String name, String lastName, int age)
    {
        this.name = name;
        this.lastName =lastName;
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public  String getName()
    {
        return  name;
    }
    public int getAge(){return age;}

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
