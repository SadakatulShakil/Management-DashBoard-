package com.laalsobuj.managementapp;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.laalsobuj.managementapp.API.ApiInterface;
import com.laalsobuj.managementapp.API.RetrofitClient;
import com.laalsobuj.managementapp.Model.LogIn.ManagementLogin;

public class LoginActivity extends AppCompatActivity {

    private TextView signIn;
    private EditText email, password;
    private ProgressBar progressBar;
    private ImageView image1, image2, image3, image4;
    TextView linkText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signIn = findViewById(R.id.signInBtn);
        email = findViewById(R.id.userEmail);
        password = findViewById(R.id.userPassword);
        progressBar = findViewById(R.id.progressBar);
        linkText = (TextView) findViewById((R.id.linkText));
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image5);

        /*image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent url1 = new Intent(Intent.ACTION_VIEW);
                url1.setData(Uri.parse("https://totthoapa.gov.bd/"));
                startActivity(url1);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent url2 = new Intent(Intent.ACTION_VIEW);
                url2.setData(Uri.parse("http://www.jms.gov.bd/"));
                startActivity(url2);
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent url3 = new Intent(Intent.ACTION_VIEW);
                url3.setData(Uri.parse("https://bfti.org.bd/"));
                startActivity(url3);
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent url5 = new Intent(Intent.ACTION_VIEW);
                url5.setData(Uri.parse("https://www.futureskyltd.com/"));
                startActivity(url5);
            }
        });

        linkText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent url6 = new Intent(Intent.ACTION_VIEW);
                url6.setData(Uri.parse("https://www.mowca.gov.bd/"));
                startActivity(url6);
            }
        });*/

        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrievedToken  = preferences.getString("TOKEN",null);
                if(retrievedToken != null){
                    Intent i = new Intent(LoginActivity.this,
                        MainActivity.class);
                startActivity(i);
                finish();
                }

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSetLoginInfo();
            }
        });
    }

    private void getSetLoginInfo() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String uEmail = email.getText().toString().trim();
        String uPassword = password.getText().toString().trim();

        if (uEmail.isEmpty() || !uEmail.matches(emailPattern)) {
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if (uPassword.isEmpty()) {
            password.setError("password is required!");
            password.requestFocus();
            return;
        }

        loginCall(uEmail, uPassword);
    }

    private void loginCall(final String uEmail,final String uPassword) {
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<ManagementLogin> signInCall = api.postByLogIn(uEmail, uPassword);

        signInCall.enqueue(new Callback<ManagementLogin>() {
            @Override
            public void onResponse(Call<ManagementLogin> call, Response<ManagementLogin> response) {
                if(response.code() == 200){
                    ManagementLogin managementLogin = response.body();
                    if(managementLogin.getStatus().equals("true")){
                        progressBar.setVisibility(View.GONE);
                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        preferences.edit().putString("TOKEN",managementLogin.getToken()).apply();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, "Please enter correct email / password", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "There is a server error", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ManagementLogin> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}