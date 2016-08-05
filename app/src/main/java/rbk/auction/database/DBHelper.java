package rbk.auction.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by DELL-PC on 04-08-2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    //Table List
    public static final String TUSER = "users";
    public static final String TITEM = "items";
    public static final String TBID = "bids";
    public static final String TAUCTION = "auctions";
    //User Table
    public static final String U_ID = "_id";
    public static final String U_NAME = "name";
    public static final String U_IMAGE = "image";
    public static final String U_EMAIL = "email";
    public static final String U_PASSWORD = "password";
    //Items Table
    public static final String I_ID = "_id";
    public static final String I_IMAGE = "image";
    public static final String I_TITLE = "title";
    public static final String I_DESCRIPTION = "description";
    public static final String I_INIT_PRICE = "init_price";
    public static final String I_USER_ID = "user_id";
    //Bid Table
    public static final String B_ID = "_id";
    public static final String B_USER_ID = "user_id";
    public static final String B_AUCTION_ID = "auction_id";
    public static final String B_PRICE = "price";
    public static final String B_TIME = "time";
    //Auction Table
    public static final String A_ID = "_id";
    public static final String A_ITEM_ID = "item_id";
    public static final String A_CREATION_TIME = "creation_time";
    public static final String A_END_TIME = "end_time";
    public static final String A_STATUS = "status";
    //Database
    static final String DATABASE = "auction.db";
    static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public static DBHelper getInstance(Context context) {
        return new DBHelper(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //USER
        db.execSQL("CREATE TABLE " + TUSER + " ( "
                + U_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + U_NAME + " text, "
                + U_IMAGE + " text, "
                + U_EMAIL + " text, "
                + U_PASSWORD + " text )");

        //ITEM
        db.execSQL("CREATE TABLE " + TITEM + " ( "
                + I_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + I_TITLE + " text, "
                + I_DESCRIPTION + " text, "
                + I_IMAGE + " text, "
                + I_INIT_PRICE + " INTEGER, "
                + I_USER_ID + " INTEGER, "
                + "FOREIGN KEY (" + I_USER_ID + ") REFERENCES " + TUSER + "(" + U_ID + "))");

        //AUCTION
        db.execSQL("CREATE TABLE " + TAUCTION + " ( "
                + A_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + A_ITEM_ID + " INTEGER, "
                + A_CREATION_TIME + " text, "
                + A_END_TIME + " text, "
                + A_STATUS + " INTEGER, "
                + "FOREIGN KEY (" + A_ITEM_ID + ") REFERENCES " + TITEM + "(" + I_ID + "))");

        //BID
        db.execSQL("CREATE TABLE " + TBID + " ( "
                + B_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + B_PRICE + " INTEGER, "
                + B_TIME + " text, "
                + B_USER_ID + " INTEGER, "
                + B_AUCTION_ID + " INTEGER, "
                + "FOREIGN KEY (" + B_USER_ID + ") REFERENCES " + TUSER + "(" + U_ID + "), "
                + "FOREIGN KEY (" + B_AUCTION_ID + ") REFERENCES " + TAUCTION + "(" + A_ID + "))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        // Drop old version tables
        db.execSQL("Drop table " + TUSER);
        db.execSQL("Drop table " + TITEM);
        db.execSQL("Drop table " + TBID);
        db.execSQL("Drop table " + TAUCTION);

        // Create New Version tables
        onCreate(db);

    }
}
