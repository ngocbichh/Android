package com.example.monanvietngon.views.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.monanvietngon.R;
import com.example.monanvietngon.data.remote.GoodDishRetrofitModule;
import com.example.monanvietngon.models.Dish;
import com.example.monanvietngon.models.IngredientsUsed;
import com.example.monanvietngon.models.SpiceUsed;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailOfDish extends AppCompatActivity {

    private android.widget.Spinner spinner;
    private List<String> list;
    private TextView textView;
    private Button btnBack;
    private ImageView dishImage;
    private Dish dish;
    private TextView tvNumberPerson;
    private TextView tvPrice;
    private TextView tvIngredients;
    private TextView tvRecipe;
    private TextView tvSpice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.detail_of_dish);

        dish = (Dish) getIntent().getSerializableExtra("item");


        textView = (TextView) findViewById(R.id.txtSoNguoi);
        btnBack = (Button) findViewById(R.id.btnBackInDetail);
        dishImage = findViewById(R.id.image);
        tvNumberPerson = findViewById(R.id.txtSoNguoi);
        tvRecipe = findViewById(R.id.tv_recipe);
        tvIngredients = findViewById(R.id.tv_ingredients);
        tvSpice = findViewById(R.id.tv_spice);

        btnBack.setOnClickListener(v -> finish());


        Glide.with(this)
                .load(dish.getThumbnailUrl())
                .into(dishImage);

        tvNumberPerson.setText("" + dish.getNumberPerson());
        tvRecipe.setText(dish.getRecipe());


        list = new ArrayList<>();
        list.add("Mời bạn chọn số người");
        list.add("1 - 2");
        list.add("3 - 4");
        list.add("5 - 6");
        spinner = (android.widget.Spinner) findViewById(R.id.spinner1);
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(arrayAdapter);
        //Bắt sự kiện khi chọn trong spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //đối số postion là vị trí phần tử trong list Data
                textView.setText(list.get(position));
                spinner.setSelection(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(DetailOfDish.this, "onNothingSelected", Toast.LENGTH_SHORT).show();
            }
        });

        loadIngredient();
        loadSpice();
    }

    private void loadSpice() {
        GoodDishRetrofitModule.getSpiceUsedService().getSpiceUsedByDishID(dish.getId()).enqueue(new Callback<List<SpiceUsed>>() {
            @Override
            public void onResponse(Call<List<SpiceUsed>> call, Response<List<SpiceUsed>> response) {
                if (response.body() == null) return;
                List<SpiceUsed> list = response.body();
                StringBuilder builder = new StringBuilder();
                for (SpiceUsed i : list) {
                    builder.append(i.toString());
                    builder.append("\n");
                }

                tvSpice.post(() -> {
                    tvSpice.setText(builder.toString());
                });
            }

            @Override
            public void onFailure(Call<List<SpiceUsed>> call, Throwable t) {

            }
        });
    }

    private void loadIngredient() {
        GoodDishRetrofitModule.getIngredientsUsedService().getIngredientUsedByDishUsed(dish.getId()).enqueue(new Callback<List<IngredientsUsed>>() {
            @Override
            public void onResponse(Call<List<IngredientsUsed>> call, Response<List<IngredientsUsed>> response) {
                if (response.body() == null) return;
                List<IngredientsUsed> list = response.body();
                StringBuilder builder = new StringBuilder();
                for (IngredientsUsed i : list) {
                    builder.append(i.toString());
                    builder.append("\n");
                }

                tvIngredients.post(() -> {
                   tvIngredients.setText(builder.toString());
                });
            }

            @Override
            public void onFailure(Call<List<IngredientsUsed>> call, Throwable t) {

            }
        });
    }
}