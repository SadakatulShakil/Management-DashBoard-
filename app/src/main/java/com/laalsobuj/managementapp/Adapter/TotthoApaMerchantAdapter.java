package com.laalsobuj.managementapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laalsobuj.managementapp.Model.TotthoApaMrPd.Result;
import com.laalsobuj.managementapp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TotthoApaMerchantAdapter extends RecyclerView.Adapter<TotthoApaMerchantAdapter.viewHolder> {
    private Context context;
    private ArrayList<Result> totthoApaMrPdArrayList;

    public TotthoApaMerchantAdapter(Context context, ArrayList<Result> totthoApaMrPdArrayList) {
        this.context = context;
        this.totthoApaMrPdArrayList = totthoApaMrPdArrayList;
    }

    @NonNull
    @Override
    public TotthoApaMerchantAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.totthoapa_merchant_view, parent, false);
        return new TotthoApaMerchantAdapter.viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TotthoApaMerchantAdapter.viewHolder holder, int position) {
    Result totthoApaWiseList = totthoApaMrPdArrayList.get(position);
    holder.apaId.setText(totthoApaWiseList.getId());
    holder.merchantName.setText(totthoApaWiseList.getFirstName());
    holder.phoneNo.setText(totthoApaWiseList.getPhoneNo());
    holder.merchantCount.setText(totthoApaWiseList.getTotalMerchants());
    holder.productCount.setText(totthoApaWiseList.getProductCount());
    }

    @Override
    public int getItemCount() {
        return totthoApaMrPdArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView apaId, merchantName, phoneNo, merchantCount, productCount;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            apaId = itemView.findViewById(R.id.apaId);
            merchantName = itemView.findViewById(R.id.merchantName);
            phoneNo = itemView.findViewById(R.id.phoneNo);
            merchantCount = itemView.findViewById(R.id.merchantCount);
            productCount = itemView.findViewById(R.id.productCount);
        }
    }
}
