package edu.csumb.pdahl.project2.model;

public class Flight {
    private String flightId;
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private String departureTime;
    private String capacity;
    private String price;

    public Flight(String flightId, String flightNumber, String departureCity, String arrivalCity, String departureTime, String capacity, String price) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.capacity = capacity;
        this.price = price;
    }

    public Flight(String flightNumber, String departureCity, String arrivalCity, String departureTime, String capacity, String price) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.capacity = capacity;
        this.price = price;
    }

    public String getFlightId() {
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

    public String getDepartureTime() {
        return departureTime;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getPrice() {
        return price;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }


}
