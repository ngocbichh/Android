package com.example.monanvietngon.views.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monanvietngon.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private LoginButton loginButtonFB;
    private Button loginButtonSDT, btnBack;
    private SignInButton signInButton;
    private TextView txtFullName;
    private ImageView avatar;
   private CallbackManager callbackManager = CallbackManager.Factory.create();
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth = FirebaseAuth.getInstance();
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.monanvietngon",                  //Insert your own package name.
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient =  GoogleSignIn.getClient(this,gso);
       // GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this,
//                        this /* OnConnectionFailedListener */)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
        connect();

        loginButtonFB.setOnClickListener(this);
        loginButtonSDT.setOnClickListener(this);
        signInButton.setOnClickListener(this);
    }

    private void loginFacebook() {

        loginButtonFB.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

               // getUserProfile(loginResult.getAccessToken());
               handleFacebookAccessToken(loginResult.getAccessToken());

            }
            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this,"Cancel",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.e("Login",exception.toString());
                Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void connect() {
        loginButtonFB = (LoginButton) findViewById(R.id.login_button_fb);
        loginButtonSDT= (Button) findViewById(R.id.login_button_sdt);
        signInButton=(SignInButton) findViewById(R.id.sign_in_button_gg);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        txtFullName = findViewById(R.id.txtFullname);
        avatar = findViewById(R.id.avatar);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            handleSignInResult(task);
        }

        else{
            callbackManager.onActivityResult(requestCode, resultCode, data);
//            txtFullName = data.getStringArrayExtra()
        }
    }



    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.login_button_fb:

               loginFacebook();
               break;
           case R.id.login_button_sdt:
               Intent number = new Intent(LoginActivity.this, NumberPhoneActivity.class);
               startActivity(number);
               break;
           case R.id.sign_in_button_gg:
                signIn();
               break;
           case R.id.btn_back:
               // quay về trang chủ
               break;
           default:
               break;
       }
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 0);
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent number = new Intent(LoginActivity.this, LoginSuccessActivity.class);
            number.putExtra("checkLogin",3);
            startActivity(number);
            // Signed in successfully, show authenticated UI.
            //updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
          //  Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
           // updateUI(null);
        }
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String s= user.getDisplayName();
                            Intent intent = new Intent(LoginActivity.this, LoginSuccessActivity.class);

                            intent.putExtra("checkLogin",1);
                            intent.putExtra("fullname",s);

                            intent.putExtra("image_url",String.valueOf(user.getPhotoUrl()));
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this,s,Toast.LENGTH_LONG) .show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("", "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                          //  updateUI(null);
                        }

                        // ...
                    }
                });
    }

}