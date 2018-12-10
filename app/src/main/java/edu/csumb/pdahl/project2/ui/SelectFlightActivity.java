package edu.csumb.pdahl.project2.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

import edu.csumb.pdahl.project2.Database.DatabaseHelper;
import edu.csumb.pdahl.project2.R;
import edu.csumb.pdahl.project2.model.Flight;

public class SelectFlightActivity extends AppCompatActivity {

    public static final int LOGIN_REQUEST = 1;
    public static final String ARG_DEPARTURE_CITY = "arg_departure_city";
    public static final String ARG_ARRIVAL_CITY = "arg_arrival_city";
    public static final String ARG_TICKET_COUNT = "arg_ticket_count";

    private List<Flight> flights;
    private RadioGroup flightsRadioGroup;
    private String ticketCount;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_flight);

        Intent intent = getIntent();
        String departureCity = intent.getStringExtra(ARG_DEPARTURE_CITY);
        String arrivalCity = intent.getStringExtra(ARG_ARRIVAL_CITY);
        ticketCount = intent.getStringExtra(ARG_TICKET_COUNT);

        db = DatabaseHelper.getInstance(getApplicationContext());
        flights = db.getFlights(departureCity, arrivalCity, Integer.valueOf(ticketCount));

        flightsRadioGroup = findViewById(R.id.radioGroup_flights);
        for (Flight flight : flights) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(flight.getFlightNumber() + " - " + flight.getDepartureTime() + " - " + flight.getCapacity() + " - $" + flight.getPrice());
            radioButton.setId(Integer.parseInt(flight.getFlightId()));
            flightsRadioGroup.addView(radioButton);
        }

        Log.d("Angel", "departure " + departureCity + "- arrival " + arrivalCity + "- ticketCount " + ticketCount);

        Button selectFlight = (Button) findViewById(R.id.button_select_ticket);
        selectFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogInActivity();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == LOGIN_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    displayConfirmation(data.getStringExtra(LoginActivity.KEY_USER_NAME),
                            data.getStringExtra(LoginActivity.KEY_USERID));
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                finish();
            }
        }

    }

    public void openLogInActivity(){
        // TODO check that radio button is selected

        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, LOGIN_REQUEST);
    }

    private void displayConfirmation(String username, final String userId) {
        final Flight selectedFlight = getSelectedFlight();
        if (selectedFlight == null) {
            return;
        }

        final String reservationId = String.valueOf(System.currentTimeMillis());
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirm Reservation");
        alert.setMessage("- Username:" + username
            + "\n-Flight number:" + selectedFlight.getFlightNumber()
            + "\n-Departure: " + selectedFlight.getDepartureCity() + ", " + selectedFlight.getDepartureTime()
            + "\n-Arrival: " + selectedFlight.getArrivalCity()
            + "\nNumber of tickets: " + ticketCount
            + "\nReservation id: " + reservationId
            + "\nTotal amount: " + selectedFlight.getPrice());
        alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // clicked Confirm --> Go back to Main Menu
                boolean result = db.addUserFlightData(userId, selectedFlight.getFlightId(), reservationId, ticketCount);
                if (result) {
                    Toast.makeText(SelectFlightActivity.this, "Reservation success!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(SelectFlightActivity.this, "Reservation already exists.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert.setCancelable(false);
        alert.create().show();
    }

    private Flight getSelectedFlight() {
        String selectedFlightId = String.valueOf(flightsRadioGroup.getCheckedRadioButtonId());
        for (Flight flight : flights) {
            if (flight.getFlightId().equals(selectedFlightId)) {
                return flight;
            }
        }

        return null;
    }

}
