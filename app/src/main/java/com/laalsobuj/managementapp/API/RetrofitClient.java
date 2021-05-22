package com.laalsobuj.managementapp.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "https://www.laalsobuj.com/api/";////field manager registration, login and location
    public static final String BASE_URL1 = "https://www.laalsobuj.com/Merchantapi/";
    public static Retrofit retrofit;
    public static Retrofit retrofit1;

    public static Retrofit getRetrofitClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;
    }

    public static Retrofit getRetrofitClient1() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();



        if (retrofit1 == null) {
            retrofit1 = new Retrofit.Builder().baseUrl(BASE_URL1)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit1;
    }

}
