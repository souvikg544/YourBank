package com.example.yourbank;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    private Activity activity;
    ArrayList name,phone,amount,ifsc,email,account;
    int position;
    CustomAdapter(Activity activity, Context context, ArrayList name, ArrayList phone, ArrayList amount,ArrayList ifsc,ArrayList email,ArrayList account)
    {
        this.activity=activity;
        this.context=context;
       // this.cust_id=cust_id;
        this.name=name;
        this.phone=phone;
        this.ifsc=ifsc;
        this.email=email;
        this.account=account;

        this.amount=amount;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.single_element,parent,false);
        return  new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.position=position;
        //holder.id_txt.setText(String.valueOf(cust_id.get(position)));
        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.phone_txt.setText(String.valueOf(phone.get(position)));
        holder.amount_txt.setText("₹ "+String.valueOf(amount.get(position)));
        holder.mlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,TransferActivity.class);
                intent.putExtra("name",String.valueOf(name.get(position)));
                intent.putExtra("phone",String.valueOf(phone.get(position)));
                intent.putExtra("amount",String.valueOf(amount.get(position)));
                intent.putExtra("ifsc",String.valueOf(ifsc.get(position)));
                intent.putExtra("account",String.valueOf(account.get(position)));
                intent.putExtra("email",String.valueOf(email.get(position)));
                intent.putExtra("arraylist",phone);
                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {

        return name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_txt,name_txt,phone_txt,amount_txt;
        LinearLayout mlayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //id_txt=itemView.findViewById(R.id.book_id);
            mlayout=itemView.findViewById(R.id.mainLayout);
            name_txt=itemView.findViewById(R.id.name);
            phone_txt=itemView.findViewById(R.id.phone);
            amount_txt=itemView.findViewById(R.id.amount);


        }
    }
}
