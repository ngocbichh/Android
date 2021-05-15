package com.example.monanvietngon.data.remote;

import com.example.monanvietngon.models.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {

    @GET("/api/cm/cmget")
    Call<List<Category>> getAllCategory();
}
