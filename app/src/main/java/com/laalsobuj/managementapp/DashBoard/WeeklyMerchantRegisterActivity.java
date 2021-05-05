package com.laalsobuj.managementapp.DashBoard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.laalsobuj.managementapp.API.ApiInterface;
import com.laalsobuj.managementapp.API.RetrofitClient;
import com.laalsobuj.managementapp.Adapter.WeeklyMerchantAdapter;
import com.laalsobuj.managementapp.Model.WeeklyMerchant.Result;
import com.laalsobuj.managementapp.Model.WeeklyMerchant.WeeklyMerchant;
import com.laalsobuj.managementapp.R;

import java.util.ArrayList;

public class WeeklyMerchantRegisterActivity extends AppCompatActivity {

    private RecyclerView weeklyDataRevView;
    private ProgressBar progressBar;
    private ImageView arrow;
    private ArrayList<Result> weeklyMerchantArrayList = new ArrayList<>();
    private WeeklyMerchantAdapter weeklyMerchantAdapter;
    private String retrievedToken;
    private SharedPreferences preferences;
    public static final String TAG = "Week";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_merchant_register);
        inItView();
        preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        retrievedToken  = preferences.getString("TOKEN",null);
        progressBar.setVisibility(View.VISIBLE);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getWeeklyData();
    }

    private void getWeeklyData() {
        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<WeeklyMerchant> weekendDataCall = api.getByWeekendList("Bearer "+retrievedToken);

        weekendDataCall.enqueue(new Callback<WeeklyMerchant>() {
            @Override
            public void onResponse(Call<WeeklyMerchant> call, Response<WeeklyMerchant> response) {
                if(response.code() == 200){
                    WeeklyMerchant weeklyMerchant = response.body();
                    Log.d(TAG, "onResponse: " +weeklyMerchant.toString());
                    weeklyMerchantArrayList.addAll(weeklyMerchant.getResult());
                }
                Log.d(TAG, "onResponse1: "+weeklyMerchantArrayList.size());
                weeklyMerchantAdapter = new WeeklyMerchantAdapter(WeeklyMerchantRegisterActivity.this, weeklyMerchantArrayList);
                weeklyDataRevView.setLayoutManager(new LinearLayoutManager(WeeklyMerchantRegisterActivity.this));
                weeklyDataRevView.setAdapter(weeklyMerchantAdapter);
                weeklyMerchantAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<WeeklyMerchant> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(WeeklyMerchantRegisterActivity.this, t.getCause().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void inItView() {
        weeklyDataRevView = findViewById(R.id.weeklyMerchantListRevView);
        progressBar = findViewById(R.id.progressBar);
        arrow = findViewById(R.id.arrow);
    }
}