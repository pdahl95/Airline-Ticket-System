package edu.csumb.pdahl.project2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.csumb.pdahl.project2.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "user.db";

    public static final String USER_TABLE = "user_table";

    public static final class ColsUser {
        public static final String UUID = "uuid";
        public static final String USERNAME = "user_name";
        public static final String PASSWORD = "password";
    }

    public static final String FLIGHT_TABLE = "flight_table";

    public static final class ColsFlight {
        public static final String UUID = "uuid";
        public static final String FLIGHTNUM = "flight_num";
        public static final String DEPARTURE = "departure";
        public static final String ARRIVAL = "arrival";
        public static final String DEPARTURETIME = "departure_time";
        public static final String CAPACITY = "capacity";
        public static final String PRICE = "price";
    }


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + USER_TABLE + "(" + ColsUser.UUID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ColsUser.USERNAME + ", "
                + ColsUser.PASSWORD
                + ")";

        String createFlightTable = "CREATE TABLE " + FLIGHT_TABLE + "(" + ColsFlight.UUID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ColsFlight.DEPARTURE + ", "
                + ColsFlight.ARRIVAL + ", "
                + ColsFlight.DEPARTURETIME + ", "
                + ColsFlight.CAPACITY + ", "
                + ColsFlight.PRICE
                + ")";

        db.execSQL(createUserTable);
        db.execSQL(createFlightTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }


    public boolean addUserData(String userName, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ColsUser.USERNAME, userName);
        contentValues.put(ColsUser.PASSWORD, password);

        long result = db.insert(USER_TABLE, null, contentValues);

        db.close();

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public User getUserData(String username, String password) {
        String whereClause = "user_name = ? AND password = ?";
        String[] whereArgs = new String[]{
                username,
                password
        };
        Cursor userCursor = queryDB(USER_TABLE, whereClause, whereArgs);

        // the database is empty!
        if (userCursor.getCount() == 0) {
            return null;
        }

        String getId = userCursor.getString(0);
        String getUserName = userCursor.getString(1);
        String getPassWord = userCursor.getString(2);

        User user = new User(getId, getUserName, getPassWord);

        userCursor.close();

        return user;
    }

    //    public void ViewData(){
//        Cursor data = userDB.showData();
//
//        if (data.getCount() == 0) {
//            display("Error", "No Data In Database.");
//            return;
//        }
//        StringBuffer buffer = new StringBuffer();
//        while (data.moveToNext()) {
//            buffer.append("ID: " + data.getString(0) + "\n");
//            buffer.append("Username: " + data.getString(1) + "\n");
//            buffer.append("Password: " + data.getString(2) + "\n");
//
//            display("All Stored Data:", buffer.toString());
//        }
//
////        data.close();
//    }
    // give access to the rows of a table (can use it for any table)
    protected Cursor queryDB(String tableName, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            return db.query(
                    tableName,
                    null,
                    whereClause,
                    whereArgs,
                    null,
                    null,
                    null
            );
        } catch (Exception e) {
            return null;
        }
    }

    public boolean addFlightData(String deptature, String arrival, String numTickets) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ColsFlight.DEPARTURE, deptature);
        contentValues.put(ColsFlight.ARRIVAL, arrival);
        contentValues.put(ColsFlight.CAPACITY, numTickets);

        long result = db.insert(FLIGHT_TABLE, null, contentValues);

        db.close();

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

//    public Cursor showData(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor data = db.rawQuery("SELECT * FROM " + USER_TABLE, null);
//        return data;
//    }
//
//    public Integer deleteData(String id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete(USER_TABLE, "ID = ?", new String[] {id});
//    }

}
