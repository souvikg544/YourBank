package com.example.yourbank;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customHistory extends RecyclerView.Adapter<customHistory.MyViewHolder> {
    Context context;
    private Activity activity;
    ArrayList date,from_name,to_name,amount,status,mid;
    int position;
    customHistory(Activity activity, Context context,ArrayList mid,ArrayList date,ArrayList from_name,ArrayList to_name,ArrayList amount,ArrayList status)
    {
        this.activity=activity;
        this.context=context;
        this.mid=mid;
        this.date=date;
        this.from_name=from_name;
        this.to_name=to_name;
        this.amount=amount;
        this.status=status;

        this.amount=amount;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.history_single,parent,false);
        return  new customHistory.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.position=position;
        //holder.id_txt.setText(String.valueOf(cust_id.get(position)));
        holder.name1.setText(String.valueOf(from_name.get(position)));
        holder.name2.setText(String.valueOf(to_name.get(position)));
        holder.amount1.setText(String.valueOf(amount.get(position)));
        holder.date1.setText(String.valueOf(date.get(position)));
        holder.status1.setText(String.valueOf(status.get(position)));

    }

    @Override
    public int getItemCount() {
        return mid.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name1,name2,amount1,date1,status1;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            name1=itemView.findViewById(R.id.name1);
            name2=itemView.findViewById(R.id.name2);
            amount1=itemView.findViewById(R.id.balance);
            date1=itemView.findViewById(R.id.date);
            status1=itemView.findViewById(R.id.transaction_status);


        }
    }
}
