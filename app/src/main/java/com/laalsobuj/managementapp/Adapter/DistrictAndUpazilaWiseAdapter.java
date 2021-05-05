package com.laalsobuj.managementapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laalsobuj.managementapp.Model.DistrictUpazilaMrPd.Result;
import com.laalsobuj.managementapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DistrictAndUpazilaWiseAdapter extends RecyclerView.Adapter<DistrictAndUpazilaWiseAdapter.viewHolder> {
    private Context context;
    private ArrayList<Result> districtUpazilaMrPdArrayList;

    public DistrictAndUpazilaWiseAdapter(Context context, ArrayList<Result> districtUpazilaMrPdArrayList) {
        this.context = context;
        this.districtUpazilaMrPdArrayList = districtUpazilaMrPdArrayList;
    }

    @NonNull
    @Override
    public DistrictAndUpazilaWiseAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.district_upazila_wise_view, parent, false);
        return new DistrictAndUpazilaWiseAdapter.viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DistrictAndUpazilaWiseAdapter.viewHolder holder, int position) {

        Result result = districtUpazilaMrPdArrayList.get(position);
        holder.merchantId.setText(result.getId());
        holder.merchantName.setText(result.getFirstName());
        holder.phoneNo.setText(result.getPhoneNo());
        holder.districtName.setText(result.getDistrict());
        holder.upazilaName.setText(result.getUpazila());
        holder.productCount.setText(result.getProductCount());
    }

    @Override
    public int getItemCount() {
        return districtUpazilaMrPdArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView merchantId, merchantName, phoneNo, districtName, upazilaName, productCount;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            merchantId = itemView.findViewById(R.id.merchantId);
            merchantName = itemView.findViewById(R.id.merchantName);
            phoneNo = itemView.findViewById(R.id.phoneNo);
            districtName = itemView.findViewById(R.id.districtName);
            upazilaName = itemView.findViewById(R.id.upazilaName);
            productCount = itemView.findViewById(R.id.productCount);
        }
    }
}
