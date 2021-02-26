package com.example.cc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewRegistrationAdapter extends RecyclerView.Adapter<ViewRegistrationAdapter.MyViewHolder> {



    ArrayList fee;
    ArrayList bid;
    ArrayList discount;
    ArrayList total;
    ArrayList agegroup;
    ArrayList batchtype;


    Context context;

    public ViewRegistrationAdapter(Context context,  ArrayList fee, ArrayList bid, ArrayList discount, ArrayList total, ArrayList agegroup,ArrayList batchtype) {
        this.context = context;
        this.bid=bid;
        this.fee=fee;
        this.discount=discount;
        this.total=total;
        this.agegroup=agegroup;
        this.batchtype=batchtype;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout_3, parent, false);
// set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.bid.setText(bid.get(position).toString());
        holder.fee.setText(fee.get(position).toString());
        holder.discount.setText(discount.get(position).toString());
        holder.total.setText(total.get(position).toString());
        holder.agegroup.setText(agegroup.get(position).toString());
        holder.batchtype.setText(batchtype.get(position).toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open another activity onitemclick
                //Intent intent = new Intent(context, AppliedBatch.class);
                //intent.putExtra("bid",bid.get(position).toString().trim());
                //context.startActivity(intent); // start Intent
            }
        });
    }



    @Override
    public int getItemCount() {
        return bid.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView bid,fee,discount,total,agegroup,batchtype;


        public MyViewHolder(View itemView) {
            super(itemView);

// get the reference of item view's
            bid = (TextView) itemView.findViewById(R.id.bid);
            fee = (TextView) itemView.findViewById(R.id.fee);
            discount = (TextView) itemView.findViewById(R.id.discount);
            total = (TextView) itemView.findViewById(R.id.total);
            agegroup = (TextView) itemView.findViewById(R.id.agegroup);
            batchtype = (TextView) itemView.findViewById(R.id.batchtype);

        }
    }
}