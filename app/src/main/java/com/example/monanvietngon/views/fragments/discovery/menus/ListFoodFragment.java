package com.example.monanvietngon.views.fragments.discovery.menus;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.monanvietngon.R;
import com.example.monanvietngon.data.remote.GoodDishRetrofitModule;
import com.example.monanvietngon.models.Dish;
import com.example.monanvietngon.views.activity.DetailOfDish;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// cai fragment nay chi chua list neu chi muon tao nhieu loai thi co the extend tu no
public class ListFoodFragment extends Fragment {

    protected RecyclerView listFood;
    private DishAdapter adapter;
    private int categoryID;

    public static ListFoodFragment newInstance(int categoryID) {

        ListFoodFragment fragment = new ListFoodFragment();
        fragment.categoryID = categoryID;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_food, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listFood = view.findViewById(R.id.list_food);
        adapter = new DishAdapter(requireContext());
        listFood.setAdapter(adapter);

        adapter.setOnClickItemListener(dish -> {
            Intent intent = new Intent(requireContext(), DetailOfDish.class);
            intent.putExtra("item", dish);
            requireContext().startActivity(intent);
        });

        loadFood();
    }

    private void loadFood() {
        Log.d("AppLog", "category id: " + categoryID);
        GoodDishRetrofitModule.getDishService().getDishByCategoryID(categoryID).enqueue(new Callback<List<Dish>>() {
            @Override
            public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
                Log.d("AppLog", "Done");
                if (response.body() == null) return;
                adapter.submitList(response.body());
            }

            @Override
            public void onFailure(Call<List<Dish>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}