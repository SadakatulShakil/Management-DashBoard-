
package com.example.managementapp.Model.DasboardResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Result implements Serializable {

    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("upazila")
    @Expose
    private String upazila;
    @SerializedName("totthoapa")
    @Expose
    private String totthoapa;
    @SerializedName("merchant")
    @Expose
    private String merchant;
    @SerializedName("customer")
    @Expose
    private String customer;
    @SerializedName("products")
    @Expose
    private String products;
    @SerializedName("weekly")
    @Expose
    private String weekly;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getUpazila() {
        return upazila;
    }

    public void setUpazila(String upazila) {
        this.upazila = upazila;
    }

    public String getTotthoapa() {
        return totthoapa;
    }

    public void setTotthoapa(String totthoapa) {
        this.totthoapa = totthoapa;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getWeekly() {
        return weekly;
    }

    public void setWeekly(String weekly) {
        this.weekly = weekly;
    }

    @Override
    public String toString() {
        return "Result{" +
                "district='" + district + '\'' +
                ", upazila='" + upazila + '\'' +
                ", totthoapa='" + totthoapa + '\'' +
                ", merchant='" + merchant + '\'' +
                ", customer='" + customer + '\'' +
                ", products='" + products + '\'' +
                ", weekly='" + weekly + '\'' +
                '}';
    }
}
