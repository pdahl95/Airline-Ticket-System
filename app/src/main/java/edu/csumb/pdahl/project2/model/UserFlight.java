package edu.csumb.pdahl.project2.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(primaryKeys = {"userId", "flightId"},
        foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "user_id",
                        childColumns = "user_id"),
                @ForeignKey(entity = Flight.class,
                        parentColumns = "flight_id",
                        childColumns = "flight_id")})
public class UserFlight {

    @ColumnInfo(name = "user_id")
    private int userId;
    @ColumnInfo(name = "flight_id")
    private int flightId;


}
