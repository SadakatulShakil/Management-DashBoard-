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
import com.laalsobuj.managementapp.Adapter.ComparisonAdapter;
import com.laalsobuj.managementapp.Model.DistrictUpazilaVsPd.DistrictUpazilaVsPd;
import com.laalsobuj.managementapp.Model.DistrictUpazilaVsPd.Result;
import com.laalsobuj.managementapp.R;

import java.util.ArrayList;

public class DistrictAndUpazilaVsProductListActivity extends AppCompatActivity {
    private RecyclerView comparisonListRevView;
    private ProgressBar progressBar;
    private ImageView arrow;
    private ArrayList<Result> comparisonArrayList = new ArrayList<>();
    private ComparisonAdapter comparisonAdapter;
    private String retrievedToken;
    private SharedPreferences preferences;
    public static final String TAG = "Comparison";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_and_upazila_vs_product_list);
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

        getComparisonList();
    }

    private void getComparisonList() {
        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<DistrictUpazilaVsPd> comparisonDataCall = api.getByDistrictUpazilaVsPdList("Bearer "+retrievedToken);

        comparisonDataCall.enqueue(new Callback<DistrictUpazilaVsPd>() {
            @Override
            public void onResponse(Call<DistrictUpazilaVsPd> call, Response<DistrictUpazilaVsPd> response) {
                if(response.code() == 200){
                    DistrictUpazilaVsPd comparisonWiseMerchant = response.body();
                    Log.d(TAG, "onResponse: " +comparisonWiseMerchant.toString());
                    comparisonArrayList.addAll(comparisonWiseMerchant.getResult());
                }
                Log.d(TAG, "onResponse1: "+comparisonArrayList.size());
                comparisonAdapter = new ComparisonAdapter(DistrictAndUpazilaVsProductListActivity.this, comparisonArrayList);
                comparisonListRevView.setLayoutManager(new LinearLayoutManager(DistrictAndUpazilaVsProductListActivity.this));
                comparisonListRevView.setAdapter(comparisonAdapter);
                comparisonAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<DistrictUpazilaVsPd> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(DistrictAndUpazilaVsProductListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void inItView() {
        comparisonListRevView = findViewById(R.id.comparisonListRevView);
        progressBar = findViewById(R.id.progressBar);
        arrow = findViewById(R.id.arrow);
    }
}