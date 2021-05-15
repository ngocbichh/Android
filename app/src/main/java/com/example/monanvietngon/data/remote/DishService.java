package com.example.monanvietngon.data.remote;

import com.example.monanvietngon.models.Dish;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DishService {

    @GET("/api/monan/monans")
    Call<List<Dish>> getAllDish();

    @GET("/api/monan/monansbychuyenmuc")
    Call<List<Dish>> getDishByCategoryID(@Query("idcm") int id);
}
