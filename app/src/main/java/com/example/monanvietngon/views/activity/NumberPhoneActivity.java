package com.example.monanvietngon.views.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.monanvietngon.R;

public class NumberPhoneActivity extends AppCompatActivity {
    private Spinner spinner;
    private EditText editText;
    private Button buttonGet, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_phone);
        connect();
        spinner.setAdapter(new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item,
                        CountryData.countryNames));
        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];

                String number = editText.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10) {
                    editText.setError("Valid number is required");
                    editText.requestFocus();
                    return;
                }

                String phoneNumber = "+" + code + number;

                Intent intent = new Intent(NumberPhoneActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("phonenumber", phoneNumber);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //quay lại màn hình đăng nhập
                startActivity(new Intent(NumberPhoneActivity.this, LoginActivity.class));
                Toast.makeText(NumberPhoneActivity.this,"Click btn back",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void connect() {
        spinner = findViewById(R.id.spinnerCountries);
        editText = findViewById(R.id.editTextPhone);
        buttonGet= findViewById(R.id.buttonContinue);
        btnBack = findViewById(R.id.btn_back);

    }
}