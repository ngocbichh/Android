package com.example.monanvietngon.views.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monanvietngon.R;
import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareLinkContent;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class LoginSuccessActivity extends AppCompatActivity {
    private TextView txtFullName;
    private ImageView imageView;
    private Button btnIntro, btnBack, btnLogout, btnShare;

    public LoginSuccessActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        txtFullName= findViewById(R.id.txtFullname);
        imageView= findViewById(R.id.avatar);
        btnIntro = (Button)findViewById(R.id.btn_giơi_thieu_ud);
        btnBack = (Button ) findViewById(R.id.btn_back);
        btnLogout = (Button) findViewById(R.id.btn_logout);
        btnShare = (Button) findViewById(R.id.btn_chia_se);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginSuccessActivity.this,"Click btn back",Toast.LENGTH_SHORT).show();
                //quay về trang chủ
            }
        });
        btnIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intro1 = new Intent(LoginSuccessActivity.this, IntroApplication.class);
                startActivity(intro1);

            }
        });

        //share
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginSuccessActivity.this,"Click share", Toast.LENGTH_LONG).show();
                ShareLinkContent content = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse("https://developers.facebook.com"))
                        .build();

            }
        });

//        connect();

        Intent intent = getIntent();
        int check =intent.getIntExtra("checkLogin",0);
        switch (check){
            case 1:
                String fullName = intent.getStringExtra("fullname");
                txtFullName.setText(fullName);

                Picasso.with(LoginSuccessActivity.this).load(intent.getStringExtra("image_url")).into(imageView);
                break;
            case 2:
            //    imageView.setImageResource(R.id.);
                txtFullName.setText(intent.getStringExtra("fullname"));
               break;
            case 3:
                GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
                txtFullName.setText(googleSignInAccount.getDisplayName());
                Picasso.with(LoginSuccessActivity.this)
                        .load(String.valueOf(googleSignInAccount.getPhotoUrl())).into(imageView);
                break;
            default:
                break;

        }
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                LoginManager.getInstance().logOut();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(LoginSuccessActivity.this, MainActivity.class));

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginSuccessActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(user!=null){
            txtFullName.setText(user.getDisplayName());
            Picasso.with(LoginSuccessActivity.this).load(String.valueOf(user.getPhotoUrl())).into(imageView);
        }


    }
//
//    private void connect()
//    {
//        txtFullName= findViewById(R.id.txtFullname);
//        imageView=findViewById(R.id.avatar);
//        btnIntro = findViewById(R.id.btn_giơi_thieu_ud);
//    }

}