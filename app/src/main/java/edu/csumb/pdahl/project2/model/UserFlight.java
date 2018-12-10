package edu.csumb.pdahl.project2.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

public class UserFlight {

    private int userId;
    private int flightId;

    public int getUserId() {
        return userId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
}
