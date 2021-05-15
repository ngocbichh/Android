package com.example.monanvietngon.data.remote;

import com.example.monanvietngon.models.Ingredients;
import com.example.monanvietngon.models.IngredientsUsed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IngredientsUsedService {

    @GET("/api/nguyenlieu/nguyenlieusbymonan")
    Call<List<IngredientsUsed>> getIngredientUsedByDishUsed(@Query("idmonan") int id);
}
