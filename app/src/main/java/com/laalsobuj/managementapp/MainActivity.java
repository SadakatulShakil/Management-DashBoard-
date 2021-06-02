package com.laalsobuj.managementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.laalsobuj.managementapp.API.ApiInterface;
import com.laalsobuj.managementapp.API.RetrofitClient;
import com.laalsobuj.managementapp.DashBoard.DistrictAndUpazilaVsProductListActivity;
import com.laalsobuj.managementapp.DashBoard.DistrictAndUpazilaWiseMrAndPdActivity;
import com.laalsobuj.managementapp.DashBoard.TotthoApaWiseMerchantAndProductActivity;
import com.laalsobuj.managementapp.DashBoard.UpazilaWiseMerchantAndProductActivity;
import com.laalsobuj.managementapp.DashBoard.WeeklyMerchantRegisterActivity;
import com.laalsobuj.managementapp.Model.DasboardResult.DashBoardResult;
import com.google.android.material.navigation.NavigationView;

import org.jsoup.Jsoup;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CardView weeklyMerchantRegisterLayout, upazilaWiseLayout, districtAndUpazilaWiseLayout, totthoApaWiseLayout, districtAndUpazilaVsListLayout;
    private Toolbar dToolbar;
    private TextView merchantCount, totthoApaCount, productsCount, districtCount, upazilaCount, weeklyRegisterCount, merchantProductCount;
    private ProgressBar progressBar;
    private String retrievedToken;
    private SharedPreferences preferences;
    private ImageView reloadPage;
    private float cVersion, lVersion;
    private String sLatestVersion, sCurrentVersion;
    public static final String TAG = "main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        retrievedToken  = preferences.getString("TOKEN",null);

        inItView();
        getDashBoardResult();
        initNavigationViewDrawer();
        new GetLatestVersion().execute();
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, dToolbar,
                R.string.drawer_open, R.string.drawer_closed);
        drawerToggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        drawerToggle.setDrawerArrowDrawable(drawerToggle.getDrawerArrowDrawable());
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        reloadPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDashBoardResult();
            }
        });
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

    private void initNavigationViewDrawer() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch ((item.getItemId())) {

                    case R.id.order:
                        Toast.makeText(MainActivity.this, "Not implement yet !", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.profile:
                        Toast.makeText(MainActivity.this, "Not implement yet !", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logOut:
                        preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        preferences.edit().putString("TOKEN", null).apply();
                        Intent logOutIntent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(logOutIntent);
                        Toast.makeText(MainActivity.this, "You are successfully logout", Toast.LENGTH_SHORT).show();
                        finish();
                        break;

                    case  R.id.appUpdate:
                        Log.d(TAG, "clickByUpdate: " + lVersion+"....."+cVersion);
                        if(lVersion > cVersion){
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("market://details?id="+MainActivity.this.getPackageName())));
                        }
                        else{
                            Toast.makeText(MainActivity.this, "আপনার অ্যাপটি আপডেট করা আছে!", Toast.LENGTH_LONG).show();
                        }
                        // Toast.makeText(this, "Not Yet Publish !", Toast.LENGTH_LONG).show();
                        break;

                    default:
                        break;
                }

                return false;
            }
        });
    }

    private static void setWindowFlag(Activity activity, final int Bits, Boolean on) {

        Window win = activity.getWindow();
        WindowManager.LayoutParams windowParams = win.getAttributes();
        if(on){
            windowParams.flags |= Bits;
        }else{
            windowParams.flags &= ~Bits;
        }
        win.setAttributes(windowParams);
    }

    private void getDashBoardResult() {
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        ApiInterface api = retrofit.create(ApiInterface.class);

        Call<DashBoardResult> dashBoardResultCall = api.getByDashBoardResult("Bearer "+retrievedToken);

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
                        merchantProductCount.setText("("+dashBoardResult.getResult().getMerchanthasproduct()+" জন মার্চেন্টের পণ্য)");
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

    /////Get Latest version////
    private class GetLatestVersion extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                sLatestVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + getPackageName() + "&hl=en")
                        .timeout(10000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
                        .first()
                        .ownText();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sLatestVersion;
        }

        @Override
        protected void onPostExecute(String s) {
            ////Get Current version////
            try {
                PackageInfo pInfo = MainActivity.this.getPackageManager().getPackageInfo(MainActivity.this.getPackageName(), 0);
                sCurrentVersion = pInfo.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            //Log.d(TAG, "onPostExecute: " +"LatestVersion: "+sLatestVersion+"......"+"CurrentVersion: "+sCurrentVersion);
            if(sLatestVersion != null){
                cVersion = Float.parseFloat(sCurrentVersion);
                lVersion = Float.parseFloat(sLatestVersion);

            }

        }
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
        reloadPage = findViewById(R.id.reloadPage);
        merchantProductCount = findViewById(R.id.merchantProductCount);

    }
}