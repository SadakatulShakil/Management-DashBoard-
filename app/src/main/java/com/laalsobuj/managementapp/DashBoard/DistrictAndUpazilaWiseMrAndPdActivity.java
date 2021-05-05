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
import com.laalsobuj.managementapp.Adapter.DistrictAndUpazilaWiseAdapter;
import com.laalsobuj.managementapp.Model.DistrictUpazilaMrPd.DistrictUpazilaMrPd;
import com.laalsobuj.managementapp.Model.DistrictUpazilaMrPd.Result;
import com.laalsobuj.managementapp.R;

import java.util.ArrayList;

public class DistrictAndUpazilaWiseMrAndPdActivity extends AppCompatActivity {
    private RecyclerView districtUpazilaWiseListRevView;
    private ProgressBar progressBar;
    private ImageView arrow;
    private ArrayList<Result> districtUpazilaMrPdArrayList = new ArrayList<>();
    private DistrictAndUpazilaWiseAdapter districtAndUpazilaWiseAdapter;
    private String retrievedToken;
    private SharedPreferences preferences;
    public static final String TAG = "DistrictAndUpazilaWise";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_and_upazila_wise_mr_and_pd);
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
        getdistrictUpazilaWiseMrPd();
    }

    private void getdistrictUpazilaWiseMrPd() {

        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<DistrictUpazilaMrPd> districtUpazilaWiseCall = api.getByDistrictUpazilaMrPdList("Bearer "+retrievedToken);

        districtUpazilaWiseCall.enqueue(new Callback<DistrictUpazilaMrPd>() {
            @Override
            public void onResponse(Call<DistrictUpazilaMrPd> call, Response<DistrictUpazilaMrPd> response) {
                if(response.code() == 200){
                    DistrictUpazilaMrPd districtUpazilaMrPd = response.body();
                    Log.d(TAG, "onResponse: " +districtUpazilaMrPd.toString());
                    districtUpazilaMrPdArrayList.addAll(districtUpazilaMrPd.getResult());
                }
                Log.d(TAG, "onResponse1: "+districtUpazilaMrPdArrayList.size());
                districtAndUpazilaWiseAdapter = new DistrictAndUpazilaWiseAdapter(DistrictAndUpazilaWiseMrAndPdActivity.this, districtUpazilaMrPdArrayList);
                districtUpazilaWiseListRevView.setLayoutManager(new LinearLayoutManager(DistrictAndUpazilaWiseMrAndPdActivity.this));
                districtUpazilaWiseListRevView.setAdapter(districtAndUpazilaWiseAdapter);
                districtAndUpazilaWiseAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<DistrictUpazilaMrPd> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(DistrictAndUpazilaWiseMrAndPdActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void inItView() {
        districtUpazilaWiseListRevView = findViewById(R.id.districtUpazilaWiseListRevView);
        progressBar = findViewById(R.id.progressBar);
        arrow = findViewById(R.id.arrow);
    }
}