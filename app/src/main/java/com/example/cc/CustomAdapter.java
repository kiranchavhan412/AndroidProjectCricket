package com.example.cc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    ArrayList batchtype;

    ArrayList agegroup;

    ArrayList bid;

    Context context;

    public CustomAdapter(Context context, ArrayList batchtype, ArrayList agegroup,ArrayList bid) {
        this.context = context;
        this.batchtype = batchtype;

        this.agegroup = agegroup;

        this.bid = bid;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
// infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_layout, parent, false);
// set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.batchtype.setText(batchtype.get(position).toString());

        holder.agegroup.setText(agegroup.get(position).toString());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

// open another activity on item click
                Intent intent = new Intent(context, BatchDetails.class);
                intent.putExtra("bid",bid.get(position).toString());
                context.startActivity(intent); // start Intent
            }
        });

    }



    @Override
    public int getItemCount() {
        return batchtype.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView batchtype;
        TextView agegroup;

        TextView bid;


        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            bid = (TextView) itemView.findViewById(R.id.bid);
            batchtype = (TextView) itemView.findViewById(R.id.batchtype);
            agegroup = (TextView) itemView.findViewById(R.id.agegroup);
            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(90); //You can manage the blinking time with this parameter
            anim.setStartOffset(90);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            agegroup.startAnimation(anim);

        }
    }
}