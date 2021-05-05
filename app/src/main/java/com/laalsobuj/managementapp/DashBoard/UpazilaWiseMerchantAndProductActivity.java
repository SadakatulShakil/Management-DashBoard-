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
import com.laalsobuj.managementapp.Adapter.UpazilaWiseAdapter;
import com.laalsobuj.managementapp.Model.UpazilaWiseMrPd.Result;
import com.laalsobuj.managementapp.Model.UpazilaWiseMrPd.UpazilaWiseMrPd;
import com.laalsobuj.managementapp.R;

import java.util.ArrayList;

public class UpazilaWiseMerchantAndProductActivity extends AppCompatActivity {

    private RecyclerView upazilaWiseListRevView;
    private ProgressBar progressBar;
    private ImageView arrow;
    private ArrayList<Result> upazilaWiseArrayList = new ArrayList<>();
    private UpazilaWiseAdapter upazilaWiseAdapter;
    private String retrievedToken;
    private SharedPreferences preferences;
    public static final String TAG = "Upazila";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upazila_wise_merchant_and_product);
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
        getUpazilaWiseList();
    }

    private void getUpazilaWiseList() {
        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<UpazilaWiseMrPd> upazilaWiseDataCall = api.getByUpazilaWiseList("Bearer "+retrievedToken);

        upazilaWiseDataCall.enqueue(new Callback<UpazilaWiseMrPd>() {
            @Override
            public void onResponse(Call<UpazilaWiseMrPd> call, Response<UpazilaWiseMrPd> response) {
                if(response.code() == 200){
                    UpazilaWiseMrPd upazilaWiseMerchant = response.body();
                    Log.d(TAG, "onResponse: " +upazilaWiseMerchant.toString());
                    upazilaWiseArrayList.addAll(upazilaWiseMerchant.getResult());
                }
                Log.d(TAG, "onResponse1: "+upazilaWiseArrayList.size());
                upazilaWiseAdapter = new UpazilaWiseAdapter(UpazilaWiseMerchantAndProductActivity.this, upazilaWiseArrayList);
                upazilaWiseListRevView.setLayoutManager(new LinearLayoutManager(UpazilaWiseMerchantAndProductActivity.this));
                upazilaWiseListRevView.setAdapter(upazilaWiseAdapter);
                upazilaWiseAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<UpazilaWiseMrPd> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(UpazilaWiseMerchantAndProductActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void inItView() {
        upazilaWiseListRevView = findViewById(R.id.upazilaWiseListRevView);
        progressBar = findViewById(R.id.progressBar);
        arrow = findViewById(R.id.arrow);
    }
}