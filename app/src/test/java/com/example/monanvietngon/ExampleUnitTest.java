package com.example.monanvietngon;

import com.example.monanvietngon.data.remote.DishService;
import com.example.monanvietngon.data.remote.GoodDishRetrofitModule;
import com.example.monanvietngon.data.remote.CategoryService;
import com.example.monanvietngon.models.Category;
import com.example.monanvietngon.models.Dish;

import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testGetAllCategory() {
        Retrofit retrofit = GoodDishRetrofitModule.getInstance();
        CategoryService categoryService = retrofit.create(CategoryService.class);
        categoryService.getAllCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                assert response.body() != null;
                assertEquals(response.body().size(), 5);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });

    }

    @Test
    public void testGetAllDish() {
        Retrofit retrofit = GoodDishRetrofitModule.getInstance();
        DishService categoryService = retrofit.create(DishService.class);
        categoryService.getAllDish().enqueue(new Callback<List<Dish>>() {
            @Override
            public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
                assert response.body() != null;
                assertEquals(response.body().size(), 10);
            }

            @Override
            public void onFailure(Call<List<Dish>> call, Throwable t) {

            }
        });

    }

    @Test
    public void testGetDishByCategoryID() {
        Retrofit retrofit = GoodDishRetrofitModule.getInstance();
        DishService categoryService = retrofit.create(DishService.class);
        categoryService.getDishByCategoryID(1).enqueue(new Callback<List<Dish>>() {
            @Override
            public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
                assert response.body() != null;
                assertEquals(response.body().size(), 2);
            }

            @Override
            public void onFailure(Call<List<Dish>> call, Throwable t) {

            }
        });

    }
}