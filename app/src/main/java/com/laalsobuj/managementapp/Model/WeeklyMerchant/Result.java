
package com.laalsobuj.managementapp.Model.WeeklyMerchant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Result implements Serializable {

    @SerializedName("weekend")
    @Expose
    private String weekend;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("merchants")
    @Expose
    private String merchants;

    public String getWeekend() {
        return weekend;
    }

    public void setWeekend(String weekend) {
        this.weekend = weekend;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMerchants() {
        return merchants;
    }

    public void setMerchants(String merchants) {
        this.merchants = merchants;
    }

    @Override
    public String toString() {
        return "Result{" +
                "weekend='" + weekend + '\'' +
                ", day='" + day + '\'' +
                ", merchants='" + merchants + '\'' +
                '}';
    }
}
