package com.example.monanvietngon.views.fragments.discovery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.monanvietngon.R;
import com.example.monanvietngon.data.remote.GoodDishRetrofitModule;
import com.example.monanvietngon.models.Category;
import com.example.monanvietngon.views.fragments.discovery.menus.ListFoodFragment;
import com.example.monanvietngon.views.widgets.sildermenu.Menu;
import com.example.monanvietngon.views.widgets.sildermenu.OnMenuChanged;
import com.example.monanvietngon.views.widgets.sildermenu.SliderMenu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DiscoverFragment extends Fragment implements OnMenuChanged {


    private SliderMenu sliderMenu;
    private List<Category> categoryList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sliderMenu = view.findViewById(R.id.slider);
        // create fragment first time create
        getChildFragmentManager().beginTransaction().replace(R.id.discover_fragment_container, new ListFoodFragment()).commit();

        sliderMenu.setOnMenuChange(this);

        loadCategory();
    }

    @Override
    public void onChange(Menu menu) {
        getChildFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.pop_enter, R.anim.fade_out) // animation khi chuyen fragment no nam trong res/anim
                .replace(R.id.discover_fragment_container, ListFoodFragment.newInstance(menu.getId()))
                .commit();
    }

    private void loadCategory() {
        GoodDishRetrofitModule.getCategoryService().getAllCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categoryList = response.body();
                List<Menu> menus = new ArrayList<>();
                for (Category c : categoryList) {
                    menus.add(new Menu(c.getId(), c.getName()));
                }
                sliderMenu.setMenu(menus);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}