package edu.csumb.pdahl.project2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.csumb.pdahl.project2.model.Flight;
import edu.csumb.pdahl.project2.model.Log;
import edu.csumb.pdahl.project2.model.TransactionType;
import edu.csumb.pdahl.project2.model.User;
import edu.csumb.pdahl.project2.model.UserFlight;

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
        public static final String RESERVATIONID = "reservation_id";
        public static final String TICKETS_COUNT = "tickets_count";
    }

    public static final String TRANSACTIONS_LOG_TABLE = "transactions_log";

    public static final class ColsTransactionLogs {
        public static final String TYPE = "type";
        public static final String TIMESTAMP = "timestamp";
        public static final String LOG = "log";
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
        String createUserTable = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + "(" + ColsUser.UUID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ColsUser.USERNAME + " UNIQUE , "
                + ColsUser.PASSWORD
                + ")";

        String createFlightTable = "CREATE TABLE IF NOT EXISTS " + FLIGHT_TABLE + "(" + ColsFlight.UUID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ColsFlight.FLIGHTNUM + " UNIQUE ,"
                + ColsFlight.DEPARTURE + ", "
                + ColsFlight.ARRIVAL + ", "
                + ColsFlight.DEPARTURETIME + ", "
                + ColsFlight.CAPACITY + ", "
                + ColsFlight.PRICE
                + ")";
        String createUserFlightTable = "CREATE TABLE IF NOT EXISTS " + USERFLIGHT_TABLE + "("
                + ColsUserFlight.FLIGHTID + ", "
                + ColsUserFlight.USERID + ", "
                + ColsUserFlight.RESERVATIONID + ", "
                + ColsUserFlight.TICKETS_COUNT + ", "
                + "PRIMARY KEY ("
                + ColsUserFlight.FLIGHTID + ","
                + ColsUserFlight.USERID + ")"
                + ")";

        String createTransactionLogs = "CREATE TABLE IF NOT EXISTS " + TRANSACTIONS_LOG_TABLE + "("
                + ColsTransactionLogs.TIMESTAMP + ", "
                + ColsTransactionLogs.TYPE + ", "
                + ColsTransactionLogs.LOG
                + ")";

        db.execSQL(createUserTable);
        db.execSQL(createFlightTable);
        db.execSQL(createUserFlightTable);
        db.execSQL(createTransactionLogs);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
//        addFlightData(new Flight("-1", "Otter101", "SF", "LA", "10:00AM", "10", "50.00"));
//        addFlightData(new Flight("-1", "Otter102", "SF", "LA", "12:00AM", "3", "50.00"));
//        addFlightData(new Flight("-1", "Otter103", "SF", "LA", "4:00AM", "10", "50.00"));
    }

    public boolean addUserFlightData(String userId, String flightId, String reservationId, String ticketCount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ColsUserFlight.FLIGHTID, flightId);
        contentValues.put(ColsUserFlight.USERID, userId);
        contentValues.put(ColsUserFlight.RESERVATIONID, reservationId);
        contentValues.put(ColsUserFlight.TICKETS_COUNT, ticketCount);

        long res = db.insert(USERFLIGHT_TABLE, null, contentValues);
        db.close();
        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }


    public void logTransaction(TransactionType type, String log) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ColsTransactionLogs.TIMESTAMP, System.currentTimeMillis());
        contentValues.put(ColsTransactionLogs.TYPE, type.toString());
        contentValues.put(ColsTransactionLogs.LOG, log);
        if (db.insert(TRANSACTIONS_LOG_TABLE, null, contentValues) <= 0) {
            android.util.Log.d("**********", "couldn't insert log to log table");
        }
    }

    public List<Log> getTransactionLogs() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TRANSACTIONS_LOG_TABLE, null);
        List<Log> logList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Log log = new Log(cursor.getString(0),TransactionType.valueOf(cursor.getString(1)), cursor.getString(2));
            logList.add(log);
        }
        cursor.close();
        return logList;
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

    public Flight getFlightById(String flightId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM flight_table WHERE uuid = ? ", new String[]{flightId});

        Flight flight;
        cursor.moveToFirst();
        flight = new Flight(cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6));
        return flight;
    }

    public boolean deleteFlightForUser(String userId, String reservationId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("user_flight_table", "user_id = ? and reservation_id = ?", new String[]{userId, reservationId}) > 0;
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
            if (noTickets <= Integer.valueOf(flight.getCapacity()) - reservationsCount) {
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
            count += Integer.parseInt(cursor.getString(3));
        }

        cursor.close();

        return count;
    }

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

        return result > 0;
    }

    public List<UserFlight> getUserFlights(String userId) {
        List<UserFlight> userFlightList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + USERFLIGHT_TABLE + " where user_id = ? ", new String[]{userId});
        while (cursor.moveToNext()) {
            UserFlight userFlight = new UserFlight(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            userFlightList.add(userFlight);
        }
        return userFlightList;
    }
}
