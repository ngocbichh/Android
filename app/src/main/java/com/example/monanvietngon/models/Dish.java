package com.example.monanvietngon.models;

import com.example.monanvietngon.data.remote.GoodDishRetrofitModule;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Dish implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("ten")
    private String name;
    @SerializedName("moTa")
    private String description;
    @SerializedName("congThuc")
    private String recipe;
    @SerializedName("soNguoi")
    private int numberPerson;
    @SerializedName("urlAnh")
    private String thumbnailUrl;
    @SerializedName("chuyenMuc")
    private Category category;

    public Dish() {
    }

    public Dish(int id, String recipe) {
        this.id = id;
        this.recipe = recipe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberPerson() {
        return numberPerson;
    }

    public void setNumberPerson(int numberPerson) {
        this.numberPerson = numberPerson;
    }

    public String getThumbnailUrl() {
        return GoodDishRetrofitModule.IMAGE_URL + thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
