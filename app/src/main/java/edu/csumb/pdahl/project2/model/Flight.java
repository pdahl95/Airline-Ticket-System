package edu.csumb.pdahl.project2.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Flight {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "flight_id")
    private int flightId;
    @ColumnInfo(name = "flight_number")
    private String flightNumber;
    @ColumnInfo(name = "departure_city")
    private String departureCity;
    @ColumnInfo(name = "arrival_city")
    private String arrivalCity;
    @ColumnInfo(name = "departure_time")
    private long departureTime;
    private int capacity;
    private double price;

    public int getFlightId() {
        return flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getPrice() {
        return price;
    }
}
