package com.chandan.mirchmasalav2.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chandan.mirchmasalav2.CustomerDetailsActivity;
import com.chandan.mirchmasalav2.DetailActivity;
import com.chandan.mirchmasalav2.R;
import com.chandan.mirchmasalav2.database.MMDbHelper;

/**
 * Created by Priya on 8/27/2016.
 */
 public class CustomerListAdapter extends RecyclerViewCursorAdapter<CustomerListAdapter.CustomerViewHolder>{
    private static final String TAG = CustomerListAdapter.class.getSimpleName();
    private final Context mContext;
    public CustomerListAdapter(Context context, Cursor cursor)
    {
        super(null);
        mContext = context;
        //Cursor cursor = database.rawQuery("SELECT * FROM Reminder ", null);
        swapCursor(cursor);
    }
    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(CustomerViewHolder holder, Cursor cursor)
    {
        //holder.avator.setImageDrawable(mPlaceAvators[position % mPlaceAvators.length]);
        String fName = cursor.getString(cursor.getColumnIndex(MMDbHelper.CUSTOMER_FNAME));
        String lName = cursor.getString(cursor.getColumnIndex(MMDbHelper.CUSTOMER_LNAME));
        String address = cursor.getString(cursor.getColumnIndex(MMDbHelper.CUSTOMER_ADDRESS));
        int sunDay = cursor.getInt(cursor.getColumnIndex(MMDbHelper.CUSTOMER_COMES_ON_MON));
        int monDay = cursor.getInt(cursor.getColumnIndex(MMDbHelper.CUSTOMER_COMES_ON_TUE));
        int tueDay = cursor.getInt(cursor.getColumnIndex(MMDbHelper.CUSTOMER_COMES_ON_WED));
        int wedDay = cursor.getInt(cursor.getColumnIndex(MMDbHelper.CUSTOMER_COMES_ON_THR));
        int thurDay = cursor.getInt(cursor.getColumnIndex(MMDbHelper.CUSTOMER_COMES_ON_FRI));
        int friDay = cursor.getInt(cursor.getColumnIndex(MMDbHelper.CUSTOMER_COMES_ON_SAT));
        int satDay = cursor.getInt(cursor.getColumnIndex(MMDbHelper.CUSTOMER_COMES_ON_SUN));
        if(monDay == 1){
            holder.btn_mon.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.circle_blue));
        }
        else{
            holder.btn_mon.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.circle_white));
        }

        if(tueDay == 1){
            holder.btn_tue.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.circle_blue));
        }
        else{
            holder.btn_tue.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.circle_white));
        }

        if(wedDay == 1){
            holder.btn_wed.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.circle_blue));
        }
        else{
            holder.btn_wed.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.circle_white));
        }

        if(thurDay == 1){
            holder.btn_thr.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.circle_blue));
        }
        else{
            holder.btn_thr.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.circle_white));
        }

        if(friDay == 1){
            holder.btn_fri.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.circle_blue));
        }
        else{
            holder.btn_fri.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.circle_white));
        }

        if(satDay == 1){
            holder.btn_sat.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.circle_blue));
        }
        else{
            holder.btn_sat.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.circle_white));
        }

        holder.name.setText(fName + " "+lName);
        holder.description.setText(address);
        //String price = cursor.getString(ShopFragment.COL_PRODUCT_PRICE);

        //Download image using picasso library
        /*Picasso.with(mContext)
                .load(imagePath)
                .resize(500, height)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.productPhoto);*/
    }

    public static class CustomerViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView avator;
        public TextView name;
        public TextView description;
        public TextView btn_mon,btn_tue,btn_wed,btn_thr,btn_fri,btn_sat,btn_sun;

        CustomerViewHolder(View itemView)
        {
            super(itemView);
            avator = (ImageView) itemView.findViewById(R.id.list_avatar);
            name = (TextView) itemView.findViewById(R.id.list_title);
            description = (TextView) itemView.findViewById(R.id.list_desc);
            btn_mon = (TextView) itemView.findViewById(R.id.btn_mon);
            btn_tue = (TextView) itemView.findViewById(R.id.btn_tue);
            btn_wed = (TextView) itemView.findViewById(R.id.btn_wed);
            btn_thr = (TextView) itemView.findViewById(R.id.btn_thr);
            btn_fri = (TextView) itemView.findViewById(R.id.btn_fri);
            btn_sat = (TextView) itemView.findViewById(R.id.btn_sat);
            btn_sun = (TextView) itemView.findViewById(R.id.btn_sun);

        }
    }

}
