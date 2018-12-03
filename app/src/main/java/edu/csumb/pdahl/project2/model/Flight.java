package edu.csumb.pdahl.project2.model;

public class Flight {
    private int flightId;
    private String flightNumber;
    private String departueCity;
    private String arrivalCity;
    private long departureTime;
    private int capacity;
    private double price;

    public Flight(int flightId, String flightNumber, String departueCity, String arrivalCity, long departureTime, int capacity, double price) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departueCity = departueCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.capacity = capacity;
        this.price = price;
    }

    public int getFlightId() {
        return flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartueCity() {
        return departueCity;
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
