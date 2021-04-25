package com.example.managementapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.managementapp.API.ApiInterface;
import com.example.managementapp.API.RetrofitClient;
import com.example.managementapp.DashBoard.DistrictAndUpazilaVsProductListActivity;
import com.example.managementapp.DashBoard.DistrictAndUpazilaWiseMrAndPdActivity;
import com.example.managementapp.DashBoard.TotthoApaWiseMerchantAndProductActivity;
import com.example.managementapp.DashBoard.UpazilaWiseMerchantAndProductActivity;
import com.example.managementapp.DashBoard.WeeklyMerchantRegisterActivity;
import com.example.managementapp.Model.DasboardResult.DashBoardResult;
import com.example.managementapp.Model.DistrictUpazilaVsPd.DistrictUpazilaVsPd;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CardView weeklyMerchantRegisterLayout, upazilaWiseLayout, districtAndUpazilaWiseLayout, totthoApaWiseLayout, districtAndUpazilaVsListLayout;
    private Toolbar dToolbar;
    private TextView merchantCount, totthoApaCount, productsCount, districtCount, upazilaCount, weeklyRegisterCount;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inItView();
        progressBar.setVisibility(View.VISIBLE);
        getDashBoardResult();

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, dToolbar,
                R.string.drawer_open, R.string.drawer_closed);
        drawerToggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        drawerToggle.setDrawerArrowDrawable(drawerToggle.getDrawerArrowDrawable());
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        weeklyMerchantRegisterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(MainActivity.this, WeeklyMerchantRegisterActivity.class);
                startActivity(i1);
            }
        });

        upazilaWiseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(MainActivity.this, UpazilaWiseMerchantAndProductActivity.class);
                startActivity(i2);
            }
        });

        totthoApaWiseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(MainActivity.this, TotthoApaWiseMerchantAndProductActivity.class);
                startActivity(i3);
            }
        });

        districtAndUpazilaWiseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(MainActivity.this, DistrictAndUpazilaWiseMrAndPdActivity.class);
                startActivity(i4);
            }
        });

        districtAndUpazilaVsListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5 = new Intent(MainActivity.this, DistrictAndUpazilaVsProductListActivity.class);
                startActivity(i5);
            }
        });


    }

    private void getDashBoardResult() {

        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<DashBoardResult> dashBoardResultCall = api.getByDashBoardResult();

        dashBoardResultCall.enqueue(new Callback<DashBoardResult>() {
            @Override
            public void onResponse(Call<DashBoardResult> call, Response<DashBoardResult> response) {
                if(response.code() == 200){
                    DashBoardResult dashBoardResult = response.body();
                    if(dashBoardResult.getStatus().equals("true")){
                        progressBar.setVisibility(View.GONE);
                        merchantCount.setText(dashBoardResult.getResult().getMerchant());
                        totthoApaCount.setText(dashBoardResult.getResult().getTotthoapa());
                        productsCount.setText(dashBoardResult.getResult().getProducts());
                        districtCount.setText(dashBoardResult.getResult().getDistrict());
                        upazilaCount.setText(dashBoardResult.getResult().getUpazila());
                        weeklyRegisterCount.setText(dashBoardResult.getResult().getWeekly());
                    }else{
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Server error / TimeOut", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Connection Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DashBoardResult> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void inItView() {
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationDrawer);
        dToolbar = findViewById(R.id.toolbar);
        weeklyMerchantRegisterLayout= findViewById(R.id.weeklyMerchantRegisterLayout);
        upazilaWiseLayout= findViewById(R.id.upazilaWiseLayout);
        totthoApaWiseLayout= findViewById(R.id.totthoApaWiseLayout);
        districtAndUpazilaVsListLayout= findViewById(R.id.districtAndUpazilaVsListLayout);
        districtAndUpazilaWiseLayout = findViewById(R.id.districtAndUpazilaWiseLayout);
        progressBar = findViewById(R.id.progressBar);
        merchantCount = findViewById(R.id.merchantCount);
        totthoApaCount = findViewById(R.id.totthoApaCount);
        productsCount = findViewById(R.id.productsCount);
        districtCount = findViewById(R.id.districtCount);
        upazilaCount = findViewById(R.id.upazilaCount);
        weeklyRegisterCount = findViewById(R.id.weeklyRegisterCount);

    }
}