package com.chandan.mirchmasalav2.Models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.chandan.mirchmasalav2.database.MMDbHelper;

/**
 * Created by Priya on 8/7/2016.
 */
public class Lorry {
    public long lorryID;
    public String mTransportName;
    public String mLorryNo;
    public String mPnoneNo;
    public String mSrcAddress;
    public String mDestAddress;
    public int mMon;
    public int mTue;
    public int mWed;
    public int mThr;
    public int mFri;
    public int mSat;
    public SQLiteDatabase db;

    public Lorry(){}
    public Lorry(SQLiteDatabase database){
        db = database;
    }
    public Lorry(String mTransportName,String mLorryNo,String mSrcAddress,String mDestAddress,String mPnoneNo){
        this.mTransportName = mTransportName;
        this.mLorryNo = mLorryNo;
        this.mSrcAddress = mSrcAddress;
        this.mDestAddress = mDestAddress;
        this.mPnoneNo = mPnoneNo;
    }

    public void saveCustomerDataToDB(){
        ContentValues insertValues = new ContentValues();
        insertValues.put(MMDbHelper.TRANSPORTER_NAME,mTransportName);
        insertValues.put(MMDbHelper.LORRY_SRC_ADDRESS,mSrcAddress);
        insertValues.put(MMDbHelper.LORRY_DEST_ADDRESS,mDestAddress);
        insertValues.put(MMDbHelper.LORRY_NO,mLorryNo);
        insertValues.put(MMDbHelper.LORRY_PHONE_NO,mPnoneNo);
        insertValues.put(MMDbHelper.LORRY_COMES_ON_MON,mMon);
        insertValues.put(MMDbHelper.LORRY_COMES_ON_TUE,mTue);
        insertValues.put(MMDbHelper.LORRY_COMES_ON_WED,mWed);
        insertValues.put(MMDbHelper.LORRY_COMES_ON_THR,mThr);
        insertValues.put(MMDbHelper.LORRY_COMES_ON_FRI,mFri);
        insertValues.put(MMDbHelper.LORRY_COMES_ON_SAT,mSat);
        lorryID = db.insert(MMDbHelper.LORRY_DETAILS_TABLE, null, insertValues);
        Log.e("test","lorryID="+lorryID);
        //saveCustomerLorryDetailsToDB();
    }

    public Cursor getAllLorryData(){
        Cursor cursor = db.rawQuery("SELECT * FROM  "+MMDbHelper.LORRY_DETAILS_TABLE, null);
        return cursor;
    }
}
