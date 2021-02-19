package com.example.yourbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;



public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME="customerlist.db";
    public static final int DATABASE_VERSION=2;
    //public static final String TABLE_NAME = "customer";
    //public static final String TABLE_NAME1 = "transaction";
   // public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IFSC = "ifsc";
    public static final String TABLE_NAME = "user_table";
    public static final String TABLE_TWO = "transfers_table";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL="email";
    public static final String COLUMN_PHONE="phone";
    public static final String COLUMN_AMOUNT="amount";

    public static final String COLUMN_ID = "_ID";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_AMOUNTNUM = "amountnum";
    public static final String COLUMN_FROM = "fromname";
    public static final String COLUMN_TO = "toname";
    public static final String COLUMN_STATUS = "status";


    public static final String COLUMN_ACCOUNT = "account";



    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_GROCERYLIST_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +

                COLUMN_PHONE + " INTEGER PRIMARY KEY NOT NULL, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_AMOUNT + " INTEGER NOT NULL, " +
                COLUMN_EMAIL + " TEXT NOT NULL, " +
                COLUMN_ACCOUNT + " TEXT NOT NULL, "+
                COLUMN_IFSC + " TEXT NOT NULL"+
                ");";
        db.execSQL(SQL_CREATE_GROCERYLIST_TABLE);

        final String SQL_HISTORY = "CREATE TABLE " +
                TABLE_TWO + " (" +

                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT NOT NULL, " +
                COLUMN_FROM + " TEXT NOT NULL, " +
                COLUMN_TO + " TEXT NOT NULL, " +
                COLUMN_AMOUNTNUM + " INTEGER NOT NULL, "+
                COLUMN_STATUS + " TEXT NOT NULL"+
                ");";

        db.execSQL(SQL_HISTORY);
        
        db.execSQL("insert into user_table values(1234567890,'Joey',9472,'joey.02@gmail.com','XXXXXXXXXXXX1234','ABC09876543')");
        db.execSQL("insert into user_table values(2345678901,'Ron',582,'ron.03@gmail.com','XXXXXXXXXXXX2341','BCA98765432')");
        db.execSQL("insert into user_table values(3456789012,'Harry',1359,'har.04@gmail.com','XXXXXXXXXXXX3412','CAB87654321')");
        db.execSQL("insert into user_table values(4567890123,'Chandler',1500,'omg.05@gmail.com','XXXXXXXXXXXX4123','ABC76543210')");
        db.execSQL("insert into user_table values(5678901234,'Monica',2603,'mon.06@gmail.com','XXXXXXXXXXXX2345','BCA65432109')");
        db.execSQL("insert into user_table values(6789012345,'Gaitonde',945,'sacred.07@gmail.com','XXXXXXXXXXXX3452','CAB54321098')");
        db.execSQL("insert into user_table values(7890123456,'Munna',5936,'mirzapur.08@gmail.com','XXXXXXXXXXXX4523','ABC43210987')");
        db.execSQL("insert into user_table values(8901234567,'Rakesh',857,'rakesh.09@gmail.com','XXXXXXXXXXXX5234','BCA32109876')");
        db.execSQL("insert into user_table values(9012345678,'Rick',4398,'rick.10@gmail.com','XXXXXXXXXXXX3456','CAB21098765')");
        db.execSQL("insert into user_table values(1234567809,'Ross',273,'ross.01@gmail.com','XXXXXXXXXXXX4563','ABC10987654')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TWO);

        onCreate(db);

    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public Cursor readHistoryData(){
        String query = "SELECT * FROM " + TABLE_TWO;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    /*int readamount(String phn)
    {
        int amount=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_PHONE + " = ?";
        String[] selectionArgs = { phn };

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = { COLUMN_AMOUNT };

        Cursor cursor = db.query(
                TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null                    // don't sort order
        );



        if (cursor.moveToNext()) {
            amount = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AMOUNT));
        }

        return amount;

    }*/
    public Cursor readparticulardata(String phonenumber)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phone = " + phonenumber, null);
        return cursor;
    }
    /*void updatedata(String am,String mphone)
    {
        //int amount = Integer.parseInt(am);
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("COLUMN_AMOUNT",am);
        long result=db.update(TABLE_NAME,cv,"phone = ?",new String[]{mphone});
        if(result==-1)
        {
            Toast.makeText(context,"Number not registered",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
        
    }*/
    public void updateAmount(String phonenumber, String amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set amount = " + amount + " where phone = " + phonenumber);
    }
    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_FROM, from_name);
        contentValues.put(COLUMN_TO, to_name);
        contentValues.put(COLUMN_AMOUNTNUM, amount);
        contentValues.put(COLUMN_STATUS, status);
        Long result = db.insert(TABLE_TWO, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
