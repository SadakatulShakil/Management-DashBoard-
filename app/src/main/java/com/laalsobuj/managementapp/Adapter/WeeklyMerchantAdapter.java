package com.laalsobuj.managementapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laalsobuj.managementapp.Model.WeeklyMerchant.Result;
import com.laalsobuj.managementapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeeklyMerchantAdapter extends RecyclerView.Adapter<WeeklyMerchantAdapter.viewHolder> {
    private Context context;
    private ArrayList<Result> weeklyMerchantArrayList;

    public WeeklyMerchantAdapter(Context context, ArrayList<Result> weeklyMerchantArrayList) {
        this.context = context;
        this.weeklyMerchantArrayList = weeklyMerchantArrayList;
    }

    @NonNull
    @Override
    public WeeklyMerchantAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.weekly_merchant_view, parent, false);
        return new WeeklyMerchantAdapter.viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyMerchantAdapter.viewHolder holder, int position) {

        Result weeklyMerchant = weeklyMerchantArrayList.get(position);

        holder.weekend.setText(weeklyMerchant.getWeekend());
        holder.day.setText(weeklyMerchant.getDay());
        holder.merchants.setText(weeklyMerchant.getMerchants());
    }

    @Override
    public int getItemCount() {
        return weeklyMerchantArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView weekend, day, merchants;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            weekend = itemView.findViewById(R.id.weekend);
            day = itemView.findViewById(R.id.day);
            merchants = itemView.findViewById(R.id.merchants);
        }
    }
}
