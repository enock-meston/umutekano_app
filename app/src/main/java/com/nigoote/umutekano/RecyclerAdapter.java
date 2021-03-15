package com.nigoote.umutekano;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<CitizenPayment> citizenPayments = new ArrayList<>();


    public RecyclerAdapter (Context context,List<CitizenPayment>  citizenPayments){
        this.mContext = context;
        this.citizenPayments = citizenPayments;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle, mCitizenId,mPhone;
        private LinearLayout mContainer;

        public MyViewHolder (View view){
            super(view);

            mTitle = view.findViewById(R.id.date);
            mCitizenId = view.findViewById(R.id.citizenId);
            mPhone = view.findViewById(R.id.umusanzu);
            mContainer = view.findViewById(R.id.product_container);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.products_list_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final CitizenPayment citizenPayment = citizenPayments.get(position);

        holder.mCitizenId.setText("Ukwezi kwa "+citizenPayment.getMonth());
        holder.mPhone.setText("Umusanzu "+citizenPayment.getFine() +" frw");
        holder.mTitle.setText(citizenPayment.getDate());


        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,DetailedProductsActivity.class);

                intent.putExtra("citizenId",citizenPayment.getcId());
                intent.putExtra("dateofpayment",citizenPayment.getDate());
                intent.putExtra("month",citizenPayment.getMonth());
                intent.putExtra("fine",citizenPayment.getFine());
                intent.putExtra("idcard",citizenPayment.getidcard());

                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return citizenPayments.size();
    }
}
