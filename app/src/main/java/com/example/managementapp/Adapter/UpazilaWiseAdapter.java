package com.example.managementapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.managementapp.Model.UpazilaWiseMrPd.Result;
import com.example.managementapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UpazilaWiseAdapter extends RecyclerView.Adapter<UpazilaWiseAdapter.viewHolder> {
    private Context context;
    private ArrayList<Result> upazilaWiseMrPdArrayList;

    public UpazilaWiseAdapter(Context context, ArrayList<Result> upazilaWiseMrPdArrayList) {
        this.context = context;
        this.upazilaWiseMrPdArrayList = upazilaWiseMrPdArrayList;
    }

    @NonNull
    @Override
    public UpazilaWiseAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.upazila_wise_data_view, parent, false);
        return new UpazilaWiseAdapter.viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UpazilaWiseAdapter.viewHolder holder, int position) {

        Result upazilaWiseMrPd = upazilaWiseMrPdArrayList.get(position);
        holder.upazilaName.setText(upazilaWiseMrPd.getUpazila());
        holder.merchantName.setText(upazilaWiseMrPd.getFirstName());
        holder.phoneNo.setText(upazilaWiseMrPd.getPhoneNo());
        holder.productCount.setText(upazilaWiseMrPd.getProductCount());
    }

    @Override
    public int getItemCount() {
        return upazilaWiseMrPdArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView upazilaName, merchantName, phoneNo, productCount;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            upazilaName = itemView.findViewById(R.id.upazilaName);
            merchantName = itemView.findViewById(R.id.merchantName);
            phoneNo = itemView.findViewById(R.id.phoneNo);
            productCount = itemView.findViewById(R.id.productCount);
        }
    }
}
