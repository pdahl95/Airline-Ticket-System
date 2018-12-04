package edu.csumb.pdahl.project2.Database;

public class AirlineTicketSchema {

    public static final class UserTable{
        public static final String NAME = "Users";
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String USERNAME = "user_name";
            public static final String PASSWORD = "password";
        }
    }

    public static final class FLightTable{
        public static final String NAME = "Flight";
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String FLIGHTNUM = "flight_num";
            public static final String DEPARTURE = "departure";
            public static final String ARRIVAL = "arrival";
            public static final String DEPARTURETIME = "departure_time";
            public static final String CAPACITY = "capacity";
            public static final String PRICE = "price";

        }
    }
}
