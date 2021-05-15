package com.example.monanvietngon.data.remote;

import com.example.monanvietngon.models.SpiceUsed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpiceService {

    @GET("/api/giavi/giavisbymonanid")
    Call<List<SpiceUsed>> getSpiceUsedByDishID(@Query("idmonan") int id);
}
