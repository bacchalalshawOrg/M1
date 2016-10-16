/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chandan.mirchmasalav2;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.chandan.mirchmasalav2.Models.Customer;
import com.chandan.mirchmasalav2.Models.Lorry;
import com.chandan.mirchmasalav2.database.MMDbHelper;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class LorryDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";
    MMDbHelper mMMDbHelper;
    SQLiteDatabase database = null;

    EditText et_transport_name,et_lorry_no,et_phone_no,et_scr,et_destination;
    CheckBox cb_mon,cb_tue,cb_wed,cb_thr,cb_fri,cb_sat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lorry_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mMMDbHelper = new MMDbHelper(getApplicationContext());
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page
        // collapsingToolbar.setTitle(getString(R.string.item_title));
        initView();
        int postion = getIntent().getIntExtra(EXTRA_POSITION, 0);
        Resources resources = getResources();
        String[] places = resources.getStringArray(R.array.places);
        collapsingToolbar.setTitle(places[postion % places.length]);

       /* String[] placeDetails = resources.getStringArray(R.array.place_details);
        TextView placeDetail = (TextView) findViewById(R.id.place_detail);
        placeDetail.setText(placeDetails[postion % placeDetails.length]);

        String[] placeLocations = resources.getStringArray(R.array.place_locations);
        TextView placeLocation =  (TextView) findViewById(R.id.place_location);
        placeLocation.setText(placeLocations[postion % placeLocations.length]);*/

        TypedArray placePictures = resources.obtainTypedArray(R.array.places_picture);
        ImageView placePicutre = (ImageView) findViewById(R.id.image);
        placePicutre.setImageDrawable(placePictures.getDrawable(postion % placePictures.length()));

        placePictures.recycle();
    }

    public void initView(){
        et_transport_name = (EditText)findViewById(R.id.et_transport_name);
        et_lorry_no = (EditText)findViewById(R.id.et_lorry_no);
        et_phone_no = (EditText)findViewById(R.id.et_phone_no);
        et_scr = (EditText)findViewById(R.id.et_scr);
        et_destination = (EditText)findViewById(R.id.et_destination);

        cb_mon = (CheckBox)findViewById(R.id.cb_mon);
        cb_tue = (CheckBox)findViewById(R.id.cb_tue);
        cb_wed = (CheckBox)findViewById(R.id.cb_wed);
        cb_thr = (CheckBox)findViewById(R.id.cb_thr);
        cb_fri = (CheckBox)findViewById(R.id.cb_fri);
        cb_sat = (CheckBox)findViewById(R.id.cb_sat);
       // setLorrySelectionData();
    }

    public void save(View view){
        Lorry lorry = new Lorry(database);
        lorry.mTransportName = et_transport_name.getText().toString();
        lorry.mLorryNo = et_lorry_no.getText().toString();
        lorry.mPnoneNo = et_phone_no.getText().toString();
        lorry.mSrcAddress = et_scr.getText().toString();
        lorry.mDestAddress = et_destination.getText().toString();
        lorry.mMon = cb_mon.isChecked() ? 1 : 0 ;
        lorry.mTue = cb_tue.isChecked() ? 1 : 0 ;
        lorry.mWed = cb_wed.isChecked() ? 1 : 0 ;
        lorry.mThr = cb_thr.isChecked() ? 1 : 0 ;
        lorry.mFri = cb_fri.isChecked() ? 1 : 0 ;
        lorry.mSat = cb_sat.isChecked() ? 1 : 0 ;
        lorry.saveCustomerDataToDB();
    }

    @Override
    public void onResume() {
        super.onResume();
        database = mMMDbHelper.getWritableDatabase();
    }

    public void onPause() {
        super.onPause();
        database.close();
    }
}
