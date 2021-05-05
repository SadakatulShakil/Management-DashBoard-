
package com.laalsobuj.managementapp.Model.DistrictUpazilaVsPd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Result implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("upazila")
    @Expose
    private String upazila;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

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

    @Override
    public String toString() {
        return "Result{" +
                "id='" + id + '\'' +
                ", product='" + product + '\'' +
                ", category='" + category + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", district='" + district + '\'' +
                ", upazila='" + upazila + '\'' +
                '}';
    }
}
