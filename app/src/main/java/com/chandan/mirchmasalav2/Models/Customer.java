package com.chandan.mirchmasalav2.Models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.chandan.mirchmasalav2.database.MMDbHelper;

import java.util.ArrayList;

/**
 * Created by Priya on 8/7/2016.
 */
public class Customer {
    public long customerId;
    public String mFirstName;
    public String mLastName;
    public String mPnoneNo;
    public String mAddress;
    public ArrayList<Lorry> mLorries;
    public ArrayList<Long> lorriesId;
    public int mSun = 0;
    public int mMon = 0;
    public int mTue = 0;
    public int mWed = 0;
    public int mThr = 0;
    public int mFri = 0;
    public int mSat = 0;
    public SQLiteDatabase db;

    public Customer(){}
    public Customer(SQLiteDatabase database){
        db = database;
    }
    public Customer(String firstName,String lastName,String address,String pnoneNo){
        mFirstName = firstName;
        mLastName = lastName;
        mAddress = address;
        mPnoneNo = pnoneNo;
    }

    public void saveCustomerDataToDB(){
        ContentValues insertValues = new ContentValues();
        insertValues.put(MMDbHelper.CUSTOMER_FNAME,mFirstName);
        insertValues.put(MMDbHelper.CUSTOMER_LNAME,mLastName);
        insertValues.put(MMDbHelper.CUSTOMER_PHONE_NO,mPnoneNo);
        insertValues.put(MMDbHelper.CUSTOMER_ADDRESS,mAddress);
        insertValues.put(MMDbHelper.CUSTOMER_COMES_ON_SUN,mSun);
        insertValues.put(MMDbHelper.CUSTOMER_COMES_ON_MON,mMon);
        insertValues.put(MMDbHelper.CUSTOMER_COMES_ON_TUE,mTue);
        insertValues.put(MMDbHelper.CUSTOMER_COMES_ON_WED,mWed);
        insertValues.put(MMDbHelper.CUSTOMER_COMES_ON_THR,mThr);
        insertValues.put(MMDbHelper.CUSTOMER_COMES_ON_FRI,mFri);
        insertValues.put(MMDbHelper.CUSTOMER_COMES_ON_SAT,mSat);
        customerId = db.insert(MMDbHelper.CUSTOMER_DETAILS_TABLE, null, insertValues);
//        Log.e("test","customerId="+customerId);
        //saveCustomerLorryDetailsToDB();
    }

    public Cursor getAllCustomerData(){
        Cursor cursor = db.rawQuery("SELECT * FROM  "+MMDbHelper.CUSTOMER_DETAILS_TABLE, null);
        return cursor;
    }

    public void saveCustomerLorryDetailsToDB(){
        for (Long temp : lorriesId) {
            ContentValues insertValues = new ContentValues();
            insertValues.put(MMDbHelper.CL_CUSTOMER_ID,customerId);
            insertValues.put(MMDbHelper.CL_LORRY_ID,customerId);
            db.insert(MMDbHelper.CUSTOMER_LORRY, null, insertValues);
        }

    }
}
