package com.example.monanvietngon.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Spice implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("donVi")
    private String unit;
    @SerializedName("gia")
    private int gia;
    @SerializedName("moTa")
    private String description;
    @SerializedName("ten")
    private String name;

    public Spice(int id, String unit, int gia, String description, String name) {
        this.id = id;
        this.unit = unit;
        this.gia = gia;
        this.description = description;
        this.name = name;
    }

    public Spice() {
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

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
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
