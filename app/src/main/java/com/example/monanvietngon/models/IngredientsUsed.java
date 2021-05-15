package com.example.monanvietngon.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IngredientsUsed implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("soLuong")
    private int amount;
    private Dish dish;
    @SerializedName("nguyenLieu")
    private Ingredients ingredients;

    public IngredientsUsed() {
    }

    public IngredientsUsed(int id, int amount, Dish dish, Ingredients ingredients) {
        this.id = id;
        this.amount = amount;
        this.dish = dish;
        this.ingredients = ingredients;
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

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return amount + " " + ingredients.toString();
    }
}
