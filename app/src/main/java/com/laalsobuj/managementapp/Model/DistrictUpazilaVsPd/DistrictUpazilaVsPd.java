
package com.laalsobuj.managementapp.Model.DistrictUpazilaVsPd;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DistrictUpazilaVsPd implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "DistrictUpazilaVsPd{" +
                "status='" + status + '\'' +
                ", result=" + result +
                '}';
    }
}
