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
import com.laalsobuj.managementapp.Adapter.TotthoApaMerchantAdapter;
import com.laalsobuj.managementapp.Model.TotthoApaMrPd.Result;
import com.laalsobuj.managementapp.Model.TotthoApaMrPd.TotthoApaMrPd;
import com.laalsobuj.managementapp.R;

import java.util.ArrayList;

public class TotthoApaWiseMerchantAndProductActivity extends AppCompatActivity {
    private RecyclerView totthoMerchantListRevView;
    private ProgressBar progressBar;
    private ImageView arrow;
    private ArrayList<Result> totthoApaWiseArrayList = new ArrayList<>();
    private TotthoApaMerchantAdapter totthoApaMerchantAdapter;
    private String retrievedToken;
    private SharedPreferences preferences;
    public static final String TAG = "TotthoApa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tottho_apa_wise_merchant_and_product);
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
        getTotthoApaMrList();
    }

    private void getTotthoApaMrList() {
        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<TotthoApaMrPd> totthoApaWiseDataCall = api.getByTotthoApaWiseList("Bearer "+retrievedToken);

        totthoApaWiseDataCall.enqueue(new Callback<TotthoApaMrPd>() {
            @Override
            public void onResponse(Call<TotthoApaMrPd> call, Response<TotthoApaMrPd> response) {
                if(response.code() == 200){
                    TotthoApaMrPd totthoApaWiseMerchant = response.body();
                    Log.d(TAG, "onResponse: " +totthoApaWiseMerchant.toString());
                    totthoApaWiseArrayList.addAll(totthoApaWiseMerchant.getResult());
                }
                Log.d(TAG, "onResponse1: "+totthoApaWiseArrayList.size());
                totthoApaMerchantAdapter = new TotthoApaMerchantAdapter(TotthoApaWiseMerchantAndProductActivity.this, totthoApaWiseArrayList);
                totthoMerchantListRevView.setLayoutManager(new LinearLayoutManager(TotthoApaWiseMerchantAndProductActivity.this));
                totthoMerchantListRevView.setAdapter(totthoApaMerchantAdapter);
                totthoApaMerchantAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<TotthoApaMrPd> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(TotthoApaWiseMerchantAndProductActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void inItView() {
        totthoMerchantListRevView = findViewById(R.id.totthoMerchantListRevView);
        progressBar = findViewById(R.id.progressBar);
        arrow = findViewById(R.id.arrow);
    }
}