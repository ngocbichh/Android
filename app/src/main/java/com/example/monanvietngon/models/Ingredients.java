package com.example.monanvietngon.models;

import com.google.gson.annotations.SerializedName;

public class Ingredients {

    @SerializedName("id")
    private int id;
    @SerializedName("donVi")
    private String unit;
    @SerializedName("gia")
    private int price;
    @SerializedName("moTa")
    private String description;
    @SerializedName("ten")
    private String name;

    public Ingredients(int id, String unit, int price, String description, String name) {
        this.id = id;
        this.unit = unit;
        this.price = price;
        this.description = description;
        this.name = name;
    }

    public Ingredients() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    @Override
    public String toString() {
        return unit + " " + name;
    }
}
