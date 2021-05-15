package com.example.monanvietngon.views.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.monanvietngon.R;
import com.example.monanvietngon.views.fragments.FavouriteFoodFragment;
import com.example.monanvietngon.views.fragments.discovery.DiscoverFragment;
import com.example.monanvietngon.views.fragments.NewFoodFragment;
import com.example.monanvietngon.views.widgets.sildermenu.Menu;
import com.example.monanvietngon.views.widgets.sildermenu.OnMenuChanged;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements OnMenuChanged {

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_nav);

        // add first fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmnet_container, new DiscoverFragment())
                .commit();
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btn_discover:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmnet_container, new DiscoverFragment())
                                .commit();
                        return true;
                    case R.id.btn_new_food:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmnet_container, new NewFoodFragment())
                                .commit();
                        return true;
                    case R.id.btn_favourite:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmnet_container, new FavouriteFoodFragment())
                                .commit();
                        // tuong tu them fragment cho favourite
                        return true;
                    case R.id.btn_account:
                        // tuong tu them fragment cho account
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if(user == null) {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(MainActivity.this, LoginSuccessActivity.class);
                            startActivity(intent);
                        }
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onChange(Menu menu) {

    }
}