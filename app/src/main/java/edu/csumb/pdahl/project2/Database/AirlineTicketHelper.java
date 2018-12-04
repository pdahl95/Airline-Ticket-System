package edu.csumb.pdahl.project2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class AirlineTicketHelper extends SQLiteOpenHelper {

    public static final String TAG = "AIRLINE";

    public static final String DATABASE_NAME = "airline_log.db";
    public static final int VERSION = 1;

    private SQLiteDatabase db;


    public AirlineTicketHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(" create table " + AirlineTicketSchema.UserTable.NAME + "(" + " _id integer primary key autoincrement, " +
                AirlineTicketSchema.UserTable.Cols.UUID + "," +
                AirlineTicketSchema.UserTable.Cols.USERNAME + "," +
                AirlineTicketSchema.UserTable.Cols.PASSWORD + "," +
                ")"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public Cursor quereyDB(String dbName, String whereClause, String[] whereArgs){
        db = this.getWritableDatabase();
        try{
            return db.query(dbName, null, whereClause, whereArgs, null, null, null);
        }catch (Exception e){
            Log.d(TAG, "Airline Ticket queryDB problems");
            return null;
        }
    }





}
