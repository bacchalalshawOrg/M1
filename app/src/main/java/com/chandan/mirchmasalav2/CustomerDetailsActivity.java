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
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chandan.mirchmasalav2.Models.Customer;
import com.chandan.mirchmasalav2.database.MMDbHelper;
import com.chandan.mirchmasalav2.multiselectspinner.KeyPairBoolData;
import com.chandan.mirchmasalav2.multiselectspinner.MultiSpinnerSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class CustomerDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";

    MMDbHelper mMMDbHelper;
    SQLiteDatabase database = null;
    EditText et_fname,et_lname,et_phone_no,et_address,et_sel_lorry;
    CheckBox cb_mon,cb_tue,cb_wed,cb_thr,cb_fri,cb_sat;
    ImageView placePicutre;
    MultiSpinnerSearch searchSpinner;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mMMDbHelper = new MMDbHelper(getApplicationContext());

        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page
        collapsingToolbar.setTitle("Cutomer Details");
        initView();
        int postion = getIntent().getIntExtra(EXTRA_POSITION, 0);
        Resources resources = getResources();

        TypedArray placePictures = resources.obtainTypedArray(R.array.places_picture);

        placePicutre.setImageDrawable(placePictures.getDrawable(postion % placePictures.length()));
        placePictures.recycle();
    }

    public void initView(){


        et_fname = (EditText)findViewById(R.id.et_fname);
        et_lname = (EditText)findViewById(R.id.et_lname);
        et_phone_no = (EditText)findViewById(R.id.et_phone_no);
        et_address = (EditText)findViewById(R.id.et_address);
        et_sel_lorry = (EditText)findViewById(R.id.et_sel_lorry);

        placePicutre = (ImageView) findViewById(R.id.image);
        cb_mon = (CheckBox)findViewById(R.id.cb_mon);
        cb_tue = (CheckBox)findViewById(R.id.cb_tue);
        cb_wed = (CheckBox)findViewById(R.id.cb_wed);
        cb_thr = (CheckBox)findViewById(R.id.cb_thr);
        cb_fri = (CheckBox)findViewById(R.id.cb_fri);
        cb_sat = (CheckBox)findViewById(R.id.cb_sat);
        setLorrySelectionData();
    }

    public void setLorrySelectionData(){
        /**
         * Getting array of String to Bind in Spinner
         */
        searchSpinner = (MultiSpinnerSearch) findViewById(R.id.searchMultiSpinner);
        final List<String> list = Arrays.asList(getResources().getStringArray(R.array.sports_array));
        TreeMap<String, Boolean> items = new TreeMap<>();
        for(String item : list) {
            items.put(item, Boolean.FALSE);
        }
        final List<KeyPairBoolData> listArray = new ArrayList<KeyPairBoolData>();

        for (int i = 0; i < list.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i + 1);
            h.setName(list.get(i));
            h.setSelected(false);
            listArray.add(h);
        }
        searchSpinner.setItems(listArray, "Multi Selection With Filter", -1, new MultiSpinnerSearch.MultiSpinnerSearchListener() {

            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {

                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {
                        Log.i("TAG", i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());
                    }
                }
            }
        });
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

    public void save(View view){
        Customer customer = new Customer(database);
        customer.mFirstName = et_fname.getText().toString();
        customer.mLastName = et_lname.getText().toString();
        customer.mPnoneNo = et_phone_no.getText().toString();
        customer.mAddress = et_address.getText().toString();
        customer.mSun = cb_mon.isChecked() ? 1 : 0 ;
        customer.mMon = cb_mon.isChecked() ? 1 : 0 ;
        customer.mTue = cb_tue.isChecked() ? 1 : 0 ;
        customer.mWed = cb_wed.isChecked() ? 1 : 0 ;
        customer.mThr = cb_thr.isChecked() ? 1 : 0 ;
        customer.mFri = cb_fri.isChecked() ? 1 : 0 ;
        customer.mSat = cb_sat.isChecked() ? 1 : 0 ;
        customer.saveCustomerDataToDB();
    }


}
