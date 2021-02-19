package com.example.yourbank;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseHelper mydb;
    ArrayList<String> phone,name,amount,ifsc,email,account;
    CustomAdapter custom;
    ImageView mback,mhistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        mydb=new DatabaseHelper(CustomerActivity.this);
        recyclerView=findViewById(R.id.recycler);
        mback=findViewById(R.id.backbutton);
        mhistory=findViewById(R.id.history);
        //id=new ArrayList<>();
        phone=new ArrayList<>();
        name=new ArrayList<>();
        amount=new ArrayList<>();
        ifsc=new ArrayList<>();
        email=new ArrayList<>();
        account=new ArrayList<>();
        displaydata();
        custom=new CustomAdapter(CustomerActivity.this,this,name,phone,amount,ifsc,email,account);
        recyclerView.setAdapter(custom);
        recyclerView.setLayoutManager(new LinearLayoutManager(CustomerActivity.this));
        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CustomerActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        mhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(CustomerActivity.this,HistoryList.class);
                startActivity(intent1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            recreate();
        }
    }

    void displaydata(){
        Cursor cursor=mydb.readAllData();
        if(cursor.getCount()==0)
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        else
        {
            while(cursor.moveToNext())
            {
                //id.add(cursor.getString(0));
                phone.add(cursor.getString(0));
                name.add(cursor.getString(1));
                amount.add(cursor.getString(2));
                ifsc.add(cursor.getString(5));
                email.add(cursor.getString(3));
                account.add(cursor.getString(4));

            }
        }

    }
}