package com.example.yourbank;

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

public class HistoryList extends AppCompatActivity {
    RecyclerView mrecyclerView;
    DatabaseHelper mydb1;
    ArrayList<String> date, from_name, to_name, amount, status, mid;
    customHistory custom;
    ImageView mimage1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);
        mydb1=new DatabaseHelper(HistoryList.this);
        mimage1=findViewById(R.id.backbutton);
        mrecyclerView = findViewById(R.id.recycle1);
        mid = new ArrayList<>();
        date = new ArrayList<>();
        from_name = new ArrayList<>();
        to_name = new ArrayList<>();
        amount = new ArrayList<>();
        status = new ArrayList<>();
        getdata();
        custom = new customHistory(HistoryList.this, this, mid, date, from_name, to_name, amount, status);
        mrecyclerView.setAdapter(custom);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(HistoryList.this));
        mimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HistoryList.this,CustomerActivity.class);
                startActivity(intent);
            }
        });


    }

    void getdata() {
        Cursor cursor = mydb1.readHistoryData();
        if (cursor.getCount() == 0)
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        else {
            while (cursor.moveToNext()) {
                //id.add(cursor.getString(0));
                mid.add(cursor.getString(0));
                date.add(cursor.getString(1));
                from_name.add(cursor.getString(2));
                amount.add(cursor.getString(4));
                to_name.add(cursor.getString(3));
                status.add(cursor.getString(5));


            }
        }
    }
}