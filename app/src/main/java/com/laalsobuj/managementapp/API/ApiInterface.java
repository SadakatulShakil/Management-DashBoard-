package com.laalsobuj.managementapp.API;

import com.laalsobuj.managementapp.Model.DasboardResult.DashBoardResult;
import com.laalsobuj.managementapp.Model.DistrictUpazilaMrPd.DistrictUpazilaMrPd;
import com.laalsobuj.managementapp.Model.DistrictUpazilaVsPd.DistrictUpazilaVsPd;
import com.laalsobuj.managementapp.Model.LogIn.ManagementLogin;
import com.laalsobuj.managementapp.Model.TotthoApaMrPd.TotthoApaMrPd;
import com.laalsobuj.managementapp.Model.UpazilaWiseMrPd.UpazilaWiseMrPd;
import com.laalsobuj.managementapp.Model.WeeklyMerchant.WeeklyMerchant;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers("accept: application/json, content-type: application/json")
    @GET("weekly-merchant-count")
    Call<WeeklyMerchant> getByWeekendList(
            @Header("Authorization") String token
    );

    @Headers("accept: application/json, content-type: application/json")
    @GET("upazila-vs-merchant-product-count")
    Call<UpazilaWiseMrPd> getByUpazilaWiseList(
            @Header("Authorization") String token
    );

    @Headers("accept: application/json, content-type: application/json")
    @GET("totthoapa-merchant-and-product-count")
    Call<TotthoApaMrPd> getByTotthoApaWiseList(
            @Header("Authorization") String token
    );

    @Headers("accept: application/json, content-type: application/json")
    @GET("district-upazila-vs-merchant-product-count")
    Call<DistrictUpazilaMrPd> getByDistrictUpazilaMrPdList(
            @Header("Authorization") String token
    );

    @Headers("accept: application/json, content-type: application/json")
    @GET("district-upazila-vs-product-list")
    Call<DistrictUpazilaVsPd> getByDistrictUpazilaVsPdList(
            @Header("Authorization") String token
    );

    @Headers("accept: application/json, content-type: application/json")
    @GET("dashboard-count")
    Call<DashBoardResult> getByDashBoardResult(
            @Header("Authorization") String token
    );

    @Headers("accept: application/json, content-type: application/json")
    @FormUrlEncoded
    @POST("management-login")
    Call<ManagementLogin> postByLogIn(
            @Field("email") String email,
            @Field("password") String password
    );
}


