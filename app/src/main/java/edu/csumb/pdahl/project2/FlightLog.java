package edu.csumb.pdahl.project2;
/**
 * Title: FlightLog.java
 * Abstract: File to actual log the flights.
 * Name: Pernille Dahl
 * Date: 2018-Dec-16
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FlightLog {

    private UUID flightId;
    String flightNum;
    String departure;
    String arrival;
    Date departureTimeLog;
    int capacity;
    double price;

    SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy @ HH:mm:ss");

    public FlightLog(){
        departureTimeLog = new Date();
        flightId = UUID.randomUUID();
    }
    public FlightLog(UUID flightId){
        this.flightId = flightId;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Date getDepartureTimeLog() {
        return departureTimeLog;
    }

    public void setDepartureTimeLog(Date departureTimeLog) {
        this.departureTimeLog = departureTimeLog;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("-=-=-=-=-=-=-=\n");
        sb.append(flightNum).append(":").append(departure).append(arrival).append(capacity).append(price).append("\n");
        sb.append(sf.format(departureTimeLog));

        return sb.toString();
    }
}
