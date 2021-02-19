package com.example.yourbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TransferActivity extends AppCompatActivity {
    TextView mname,mphone,mamount,maccount,mifsc,memail;
    Button mtransfer;
    String mname1,mphone1,mamount1,maccount1,mifsc1,memail1;
    EditText meditamount,meditphone;
    String phn,amt;
    int recipentAmt,amtint,amtfin,amtlft;
    Cursor cursor1;
    String selectuser_phonenumber, selectuser_name, selectuser_balance, date;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    ArrayList mph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        mname=findViewById(R.id.name1_last);
        mphone=findViewById(R.id.phone_last);
        mamount=findViewById(R.id.amount1_last);
        mifsc=findViewById(R.id.ifsc_last);
        maccount=findViewById(R.id.number_last);
        memail=findViewById(R.id.email_last);
        mtransfer=findViewById(R.id.button2);
        meditphone=findViewById(R.id.edit_phone);
        meditamount=findViewById(R.id.edit_amount);
        getIntentExtra();
        mtransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper mydb=new DatabaseHelper(TransferActivity.this);
                phn=meditphone.getText().toString().trim();
                amt=meditamount.getText().toString().trim();

                amtint=Integer.parseInt(amt);
                amtlft=Integer.parseInt(mamount1)-amtint;
                if(amtlft <0){
                    Toast.makeText(TransferActivity.this,"Not Enough Balence",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mph.contains(phn)==false)
                {
                    Toast.makeText(TransferActivity.this,"Phone number does not exist",Toast.LENGTH_SHORT).show();
                    return;

                }
                if(phn == null|| amt == null)
                {
                    Toast.makeText(TransferActivity.this,"Fields can not be empty",Toast.LENGTH_SHORT).show();
                    return;

                }


                cursor1=mydb.readparticulardata(phn);
                Context context = getApplicationContext();
                /*if(!cursor1.moveToFirst()) {
                    Toast.makeText(context,"Number not registered",Toast.LENGTH_SHORT).show();
                }*/
                while(cursor1.moveToNext()) {
                    selectuser_name = cursor1.getString(1);
                    selectuser_balance = cursor1.getString(2);
                }
                recipentAmt=Integer.parseInt(selectuser_balance);
                amtfin=amtint+recipentAmt;
                mydb.updateAmount(phn,String.valueOf(amtfin));
                mydb.updateAmount(mphone1,String.valueOf(amtlft));
                calendar = Calendar.getInstance();
                simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a");
                String date = simpleDateFormat.format(calendar.getTime());
                boolean b=new DatabaseHelper(TransferActivity.this).insertTransferData(date, mname1, selectuser_name, amt, "Success");

                startActivity(new Intent(TransferActivity.this,CustomerActivity.class));

            }
        });


    }

    void getIntentExtra()
    {
        if(getIntent().hasExtra("name")){
            mname1=getIntent().getStringExtra("name");
            mphone1=getIntent().getStringExtra("phone");
            mamount1=getIntent().getStringExtra("amount");
            mifsc1=getIntent().getStringExtra("ifsc");
            maccount1=getIntent().getStringExtra("account");
            memail1=getIntent().getStringExtra("email");
            mph=getIntent().getStringArrayListExtra("arraylist");
            mname.setText("Name: "+mname1);
            mphone.setText("Phone Number: "+mphone1);
            mamount.setText("Amount: "+mamount1);
            mifsc.setText("Ifsc: "+mifsc1);
            maccount.setText("Account Number: "+maccount1);
            memail.setText("Email id: "+memail1);



        }
        else
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
    }
}