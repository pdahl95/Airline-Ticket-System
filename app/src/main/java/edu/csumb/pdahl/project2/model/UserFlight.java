package edu.csumb.pdahl.project2.model;

public class UserFlight {

    private String userId;
    private String flightId;
    private String reservationId;
    private String ticketCount;

    public UserFlight(String flightId, String userId , String reservationId, String ticketCount) {
        this.flightId = flightId;
        this.userId = userId;
        this.reservationId = reservationId;
        this.ticketCount = ticketCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(String ticketCount) {
        this.ticketCount = ticketCount;
    }
}
