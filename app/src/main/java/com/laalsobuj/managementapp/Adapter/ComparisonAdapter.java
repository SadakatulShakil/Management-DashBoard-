package com.laalsobuj.managementapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laalsobuj.managementapp.Model.DistrictUpazilaVsPd.Result;
import com.laalsobuj.managementapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ComparisonAdapter extends RecyclerView.Adapter<ComparisonAdapter.viewHolder> {
    private Context context;
    private ArrayList<Result> districtUpazilaVsPdArrayList;

    public ComparisonAdapter(Context context, ArrayList<Result> districtUpazilaVsPdArrayList) {
        this.context = context;
        this.districtUpazilaVsPdArrayList = districtUpazilaVsPdArrayList;
    }

    @NonNull
    @Override
    public ComparisonAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comparison_view, parent, false);
        return new ComparisonAdapter.viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ComparisonAdapter.viewHolder holder, int position) {
    Result districtUpazilaVsPd = districtUpazilaVsPdArrayList.get(position);
    holder.userId.setText(districtUpazilaVsPd.getId());
    holder.productName.setText(districtUpazilaVsPd.getProduct());
    holder.categoryName.setText(districtUpazilaVsPd.getCategory());
    holder.merchantName.setText(districtUpazilaVsPd.getFirstName());
    holder.phoneNo.setText(districtUpazilaVsPd.getPhoneNo());
    holder.districtName.setText(districtUpazilaVsPd.getDistrict());
    holder.upazilaName.setText(districtUpazilaVsPd.getUpazila());
    }

    @Override
    public int getItemCount() {
        return districtUpazilaVsPdArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView userId, productName, categoryName, merchantName, phoneNo, districtName, upazilaName;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.userId);
            productName = itemView.findViewById(R.id.productName);
            categoryName = itemView.findViewById(R.id.categoryName);
            merchantName = itemView.findViewById(R.id.merchantName);
            phoneNo = itemView.findViewById(R.id.phoneNo);
            districtName = itemView.findViewById(R.id.districtName);
            upazilaName = itemView.findViewById(R.id.upazilaName);
        }
    }
}
