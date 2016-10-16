package com.chandan.mirchmasalav2;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chandan.mirchmasalav2.Models.Customer;
import com.chandan.mirchmasalav2.Models.Lorry;
import com.chandan.mirchmasalav2.adapters.CustomerListAdapter;
import com.chandan.mirchmasalav2.adapters.LorryListAdapter;
import com.chandan.mirchmasalav2.database.MMDbHelper;

/**
 * Provides UI for the view with List.
 */
public class ListLorryFragment extends Fragment {
    MMDbHelper mMMDbHelper;
    SQLiteDatabase mDatabase;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMMDbHelper = new MMDbHelper(getActivity().getApplicationContext());
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }

    @Override
    public void onResume(){
        super.onResume();
        mDatabase = mMMDbHelper.getWritableDatabase();
        LorryListAdapter adapter = new LorryListAdapter(getActivity().getApplicationContext(),new Lorry(mDatabase).getAllLorryData());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onPause(){
        super.onPause();
        mDatabase.close();
    }
}
