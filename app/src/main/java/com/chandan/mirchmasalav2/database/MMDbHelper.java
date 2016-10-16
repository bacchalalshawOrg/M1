package com.chandan.mirchmasalav2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Priya on 12/26/2015.
 */
public class MMDbHelper extends SQLiteOpenHelper {

    private static MMDbHelper mInstance = null;

    // Database Version
    private Context mContext;
    public static final int DATABASE_VERSION = 1;
    // Database Name
    public static final String DATABASE_NAME = "mm_db_helper";
    // Table customer Details
    public static final String CUSTOMER_DETAILS_TABLE = "CustomerDetails";
    // Customer details column
    public static final String CUSTOMER_ID = "_id";
    public static final String CUSTOMER_FNAME = "fname";
    public static final String CUSTOMER_LNAME = "lname";
    public static final String CUSTOMER_PHOTO = "photo";
    public static final String CUSTOMER_PHONE_NO= "phone_no";
    public static final String CUSTOMER_ADDRESS = "address";
    public static final String CUSTOMER_COMES_ON_SUN = "sun";
    public static final String CUSTOMER_COMES_ON_MON = "mon";
    public static final String CUSTOMER_COMES_ON_TUE = "tue";
    public static final String CUSTOMER_COMES_ON_WED = "wed";
    public static final String CUSTOMER_COMES_ON_THR = "thr";
    public static final String CUSTOMER_COMES_ON_FRI = "fri";
    public static final String CUSTOMER_COMES_ON_SAT = "sat";

    // Table LORRY Details
    public static final String LORRY_DETAILS_TABLE = "LorryDetails";
    // LORRY details column
    public static final String LORRY_ID = "_id";
    public static final String TRANSPORTER_NAME = "transporter_name";
    public static final String LORRY_PHOTO = "photo";
    public static final String LORRY_NO = "lorry_no";
    public static final String LORRY_PHONE_NO= "phone_no";
    public static final String LORRY_SRC_ADDRESS = "source";
    public static final String LORRY_DEST_ADDRESS= "destination";
    public static final String LORRY_CAPACITY = "capacity";
    public static final String LORRY_COMES_ON_SUN = "sun";
    public static final String LORRY_COMES_ON_MON = "mon";
    public static final String LORRY_COMES_ON_TUE = "tue";
    public static final String LORRY_COMES_ON_WED = "wed";
    public static final String LORRY_COMES_ON_THR = "thr";
    public static final String LORRY_COMES_ON_FRI = "fri";
    public static final String LORRY_COMES_ON_SAT = "sat";

    // Table CUSTOMER_LORRY Details
    public static final String CUSTOMER_LORRY = "CustomerLorry";
    // CUSTOMER LORRY details column
    public static final String CUSTOMER_LORRY_ID = "_id";
    public static final String CL_CUSTOMER_ID = "customer_id";
    public static final String CL_LORRY_ID = "lorry_id";

    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static factory method "getInstance()" instead.
     */
    public MMDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext=context;
    }

    public static MMDbHelper getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new MMDbHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CUSTOMER_DETAILS_TABLE = "CREATE TABLE " + CUSTOMER_DETAILS_TABLE + "("
                + CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CUSTOMER_FNAME + " TEXT, "
                + CUSTOMER_LNAME + " TEXT, "
                + CUSTOMER_PHOTO + " TEXT, "
                + CUSTOMER_PHONE_NO + " TEXT, "
                + CUSTOMER_ADDRESS + " TEXT , "
                + CUSTOMER_COMES_ON_SUN + " INTEGER, "
                + CUSTOMER_COMES_ON_MON + " INTEGER, "
                + CUSTOMER_COMES_ON_TUE + " INTEGER, "
                + CUSTOMER_COMES_ON_WED + " INTEGER, "
                + CUSTOMER_COMES_ON_THR + " INTEGER, "
                + CUSTOMER_COMES_ON_FRI + " INTEGER, "
                + CUSTOMER_COMES_ON_SAT + " INTEGER "
                + " )";

        Log.e("test","CREATE_CUSTOMER_DETAILS_TABLE table "+CREATE_CUSTOMER_DETAILS_TABLE);

        String CREATE_LORRY_DETAILS_TABLE = "CREATE TABLE " + LORRY_DETAILS_TABLE + "("
                + LORRY_ID + " INTEGER PRIMARY KEY , "
                + LORRY_NO + " TEXT, "
                + TRANSPORTER_NAME + " TEXT, "
                + LORRY_PHOTO + " TEXT, "
                + LORRY_SRC_ADDRESS + " TEXT , "
                + LORRY_DEST_ADDRESS + " TEXT , "
                + LORRY_PHONE_NO + " TEXT , "

                + LORRY_CAPACITY + " TEXT , "
                + LORRY_COMES_ON_SUN + " INTEGER, "
                + LORRY_COMES_ON_MON + " INTEGER, "
                + LORRY_COMES_ON_TUE + " INTEGER, "
                + LORRY_COMES_ON_WED + " INTEGER, "
                + LORRY_COMES_ON_THR + " INTEGER, "
                + LORRY_COMES_ON_FRI + " INTEGER, "
                + LORRY_COMES_ON_SAT + " INTEGER "
                + " )";

        Log.e("test", "CREATE_LORRY_DETAILS_TABLE " + CREATE_LORRY_DETAILS_TABLE);

        String CREATE_CUSTOMER_LORRY_TABLE = "CREATE TABLE " + CUSTOMER_LORRY + "("
                + CUSTOMER_LORRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CL_CUSTOMER_ID + " TEXT, "
                + CL_LORRY_ID + " TEXT "
                + " )";
        Log.e("test", "CREATE_CUSTOMER_LORRY_TABLE " + CREATE_CUSTOMER_LORRY_TABLE);

        db.execSQL(CREATE_CUSTOMER_DETAILS_TABLE);
        db.execSQL(CREATE_LORRY_DETAILS_TABLE);
        db.execSQL(CREATE_CUSTOMER_LORRY_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
