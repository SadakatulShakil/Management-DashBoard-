
package com.example.managementapp.Model.TotthoApaMrPd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Result implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("total_merchants")
    @Expose
    private String totalMerchants;
    @SerializedName("product_count")
    @Expose
    private String productCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTotalMerchants() {
        return totalMerchants;
    }

    public void setTotalMerchants(String totalMerchants) {
        this.totalMerchants = totalMerchants;
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
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", totalMerchants='" + totalMerchants + '\'' +
                ", productCount='" + productCount + '\'' +
                '}';
    }
}
