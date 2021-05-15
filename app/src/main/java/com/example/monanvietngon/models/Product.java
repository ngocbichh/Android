package com.example.monanvietngon.models;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String description;
    private String name;
    private double price;

    public Product(int id, String description, String name, double price) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
