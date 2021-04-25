package com.example.managementapp.API;

import com.example.managementapp.Model.DasboardResult.DashBoardResult;
import com.example.managementapp.Model.DistrictUpazilaMrPd.DistrictUpazilaMrPd;
import com.example.managementapp.Model.DistrictUpazilaVsPd.DistrictUpazilaVsPd;
import com.example.managementapp.Model.TotthoApaMrPd.TotthoApaMrPd;
import com.example.managementapp.Model.UpazilaWiseMrPd.UpazilaWiseMrPd;
import com.example.managementapp.Model.WeeklyMerchant.WeeklyMerchant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInterface {

    @Headers("accept: application/json, content-type: application/json")
    @GET("weekly-merchant-count")
    Call<WeeklyMerchant> getByWeekendList();

    @Headers("accept: application/json, content-type: application/json")
    @GET("upazila-vs-merchant-product-count")
    Call<UpazilaWiseMrPd> getByUpazilaWiseList();

    @Headers("accept: application/json, content-type: application/json")
    @GET("totthoapa-merchant-and-product-count")
    Call<TotthoApaMrPd> getByTotthoApaWiseList();

    @Headers("accept: application/json, content-type: application/json")
    @GET("district-upazila-vs-merchant-product-count")
    Call<DistrictUpazilaMrPd> getByDistrictUpazilaMrPdList();

    @Headers("accept: application/json, content-type: application/json")
    @GET("district-upazila-vs-product-list")
    Call<DistrictUpazilaVsPd> getByDistrictUpazilaVsPdList();

    @Headers("accept: application/json, content-type: application/json")
    @GET("dashboard-count")
    Call<DashBoardResult> getByDashBoardResult();
}


