
package com.example.managementapp.Model.UpazilaWiseMrPd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Result implements Serializable {

    @SerializedName("upazila")
    @Expose
    private String upazila;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("product_count")
    @Expose
    private String productCount;

    public String getUpazila() {
        return upazila;
    }

    public void setUpazila(String upazila) {
        this.upazila = upazila;
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

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    @Override
    public String toString() {
        return "Result{" +
                "upazila='" + upazila + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", productCount='" + productCount + '\'' +
                '}';
    }
}
