package com.example.monanvietngon.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SpiceUsed implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("soLuong")
    private int amount;
    @SerializedName("giaVi")
    private Spice spice;
    @SerializedName("monAn")
    private Dish dish;

    public SpiceUsed(int id, int amount, Spice spice, Dish dish) {
        this.id = id;
        this.amount = amount;
        this.spice = spice;
        this.dish = dish;
    }

    public SpiceUsed() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Spice getSpice() {
        return spice;
    }

    public void setSpice(Spice spice) {
        this.spice = spice;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public String toString() {
        return amount + " " + spice.toString();
    }
}
