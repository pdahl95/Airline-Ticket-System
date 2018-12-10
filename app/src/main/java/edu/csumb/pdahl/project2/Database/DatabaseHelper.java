package edu.csumb.pdahl.project2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.csumb.pdahl.project2.model.Flight;
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

    public static final String USERFLIGHT_TABLE = "user_flight_table";

    public static final class ColsUserFlight {
        public static final String FLIGHTID = "flight_id";
        public static final String USERID = "user_id";
    }

    private static DatabaseHelper INSTANCE;

    public static DatabaseHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseHelper(context);
        }

        return INSTANCE;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + USER_TABLE + "(" + ColsUser.UUID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ColsUser.USERNAME + ", "
                + ColsUser.PASSWORD
                + ")";

        String createFlightTable = "CREATE TABLE " + FLIGHT_TABLE + "(" + ColsFlight.UUID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ColsFlight.FLIGHTNUM + ","
                + ColsFlight.DEPARTURE + ", "
                + ColsFlight.ARRIVAL + ", "
                + ColsFlight.DEPARTURETIME + ", "
                + ColsFlight.CAPACITY + ", "
                + ColsFlight.PRICE
                + ")";
        String createUserFlightTable = "CREATE TABLE " + USERFLIGHT_TABLE + "("
                + ColsUserFlight.FLIGHTID + ", "
                + ColsUserFlight.USERID + ", "
                + "PRIMARY KEY ("
                + ColsUserFlight.FLIGHTID + ","
                + ColsUserFlight.USERID + ")"
                + ")";


        db.execSQL(createUserTable);
        db.execSQL(createFlightTable);
        db.execSQL(createUserFlightTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    public boolean addUserFlightData(String userId, String flightId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ColsUserFlight.FLIGHTID, flightId);
        contentValues.put(ColsUserFlight.USERID, userId);

        long res = db.insert(USERFLIGHT_TABLE, null, contentValues);
        db.close();
        if (res == -1) {
            return false;
        } else {
            return true;
        }
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
        if (userCursor.getCount() <= 0) {
            return null;
        }

        userCursor.moveToFirst();
        String getId = userCursor.getString(0);
        String getUserName = userCursor.getString(1);
        String getPassWord = userCursor.getString(2);

        User user = new User(getId, getUserName, getPassWord);

        userCursor.close();

        return user;
    }

    public List<Flight> getReservationsByUserID(String userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM flight_table AS F " +
                "INNER JOIN user_flight_table ON F.flight_id = flight_id " +
                "WHERE user_id = ?", new String[]{userId});

        List<Flight> listOfFlights = new ArrayList<>();
        Flight flight;

        // parse data and crate a flight
        while (cursor.moveToNext()) {
            flight = createFlightObject(cursor);

            listOfFlights.add(flight);
        }

        return listOfFlights;
    }

    private Flight createFlightObject(Cursor cursor) {
        String getId = cursor.getString(0);
        String getFlightNum = cursor.getString(1);
        String getDeptCity = cursor.getString(2);
        String getArrCity = cursor.getString(3);
        String getTimeOfDept = cursor.getString(4);
        String getCapacity = cursor.getString(5);
        String getPrice = cursor.getString(6);

        return new Flight(getId, getFlightNum, getDeptCity, getArrCity, getTimeOfDept, getCapacity, getPrice);
    }

    public List<Flight> getFlights(String departureCity, String arrivalCity, int noTickets) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM flight_table AS F " +
                        "WHERE " + ColsFlight.DEPARTURE + " = ? AND " + ColsFlight.ARRIVAL + " = ?",
                new String[]{departureCity, arrivalCity});

        List<Flight> flights = new ArrayList<>();
        while (cursor.moveToNext()) {
            Flight flight = createFlightObject(cursor);
            int reservationsCount = getReservationsCount(flight.getFlightId());
            if (noTickets < Integer.valueOf(flight.getCapacity()) - reservationsCount) {
                flights.add(flight);
            }
        }

        return flights;
    }

    private int getReservationsCount(String flightId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user_flight_table AS F " +
                "WHERE flight_id = ?", new String[]{flightId});

        int count = 0;

        while (cursor.moveToNext()) {
            count++;
        }

        cursor.close();

        return count;
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
    private Cursor queryDB(String tableName, String whereClause, String[] whereArgs) {
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

    public boolean addFlightData(Flight flight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ColsFlight.DEPARTURE, flight.getDepartureCity());
        contentValues.put(ColsFlight.ARRIVAL, flight.getArrivalCity());
        contentValues.put(ColsFlight.CAPACITY, flight.getCapacity());
        contentValues.put(ColsFlight.FLIGHTNUM, flight.getFlightNumber());
        contentValues.put(ColsFlight.DEPARTURETIME, flight.getDepartureTime());
        contentValues.put(ColsFlight.PRICE, flight.getPrice());

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
