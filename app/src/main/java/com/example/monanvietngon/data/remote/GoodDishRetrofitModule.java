package com.example.monanvietngon.data.remote;

import com.example.monanvietngon.models.Ingredients;
import com.example.monanvietngon.models.IngredientsUsed;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoodDishRetrofitModule {

    private static Retrofit instance = null;
    public static final String BASE_URL = "http://192.168.0.106:8080/";
    public static final String IMAGE_URL = "http://192.168.0.106:8080/api/monan/image/";

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new  Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

    public static CategoryService getCategoryService() {
        return getInstance().create(CategoryService.class);
    }

    public static DishService getDishService() {
        return getInstance().create(DishService.class);
    }

    public static IngredientsUsedService getIngredientsUsedService() {
        return getInstance().create(IngredientsUsedService.class);
    }

    public static SpiceService getSpiceUsedService() {
        return getInstance().create(SpiceService.class);
    }
}
