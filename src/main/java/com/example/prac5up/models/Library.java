package com.example.prac5up.models;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "library")
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private int capacity;
    private String description;

    @OneToMany(mappedBy = "library")
    private List<Book> books;

    public Library(){}

    public Library(Long id, String name, String location, int capacity, String description, List<Book> books) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.description = description;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}