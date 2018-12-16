package edu.csumb.pdahl.project2.ui;
/**
 * Title: ReservationsActivity.java
 * Abstract: This file is in the cancel reservation flow. This file displays the possible
 * flights for the user to cancel. The user is allowed to cancel the flow, and keep the reservation.
 * Name: Pernille Dahl
 * Date: 2018-Dec-16
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

import edu.csumb.pdahl.project2.Database.DatabaseHelper;
import edu.csumb.pdahl.project2.R;
import edu.csumb.pdahl.project2.model.Flight;
import edu.csumb.pdahl.project2.model.TransactionType;
import edu.csumb.pdahl.project2.model.UserFlight;

public class ReservationsActivity extends AppCompatActivity {

    public static final String USER_KEY = "user_key";
    public static final String KEY_USER_NAME = "key_user_name";


    private DatabaseHelper db;
    private RadioGroup displayReservationRadioGroup;
    Button cancelReservationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        final String userIdArg = getUserIdArg();

        db = DatabaseHelper.getInstance(getApplicationContext());
        List<UserFlight> userFlights = db.getUserFlights(userIdArg);
        if(userFlights.size() == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(ReservationsActivity.this);
            alert.setTitle("No Reservation!");
            alert.setMessage("There is no reservation with this username");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.create().show();
        }
        displayReservationRadioGroup = findViewById(R.id.radioGroup_reservations);
        for (UserFlight userFlight : userFlights) {
            RadioButton radioButton = new RadioButton(this);
            Flight flight = db.getFlightById(userFlight.getFlightId());
            radioButton.setText("Reservation Number: " + userFlight.getReservationId()
                    + "\nFlight Number: " + flight.getFlightNumber()
                    + "\n Departure/Arrival: " + flight.getDepartureCity() + ", " + flight.getArrivalCity()
                    + "\nDeparture at " + flight.getDepartureTime()
                    + "\nNumber of Tickets Reserved- " + userFlight.getTicketCount()
                    + "\n"
            );
            radioButton.setId(Integer.parseInt(flight.getFlightId()));
            displayReservationRadioGroup.addView(radioButton);
        }
        cancelReservationButton = (Button) findViewById(R.id.button_cancel_ticket);
        cancelReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int id = displayReservationRadioGroup.getCheckedRadioButtonId();
                if (id < 0) {
                    Toast.makeText(ReservationsActivity.this, "No Reservations information!", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder alert = new AlertDialog.Builder(ReservationsActivity.this);
                    alert.setTitle("No Information");
                    alert.setMessage("There is no record of reservations!");
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // clicked ok, then user should be able to reenter to serve a seat!
                            finish();
                        }
                    });
                    alert.create().show();

                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(ReservationsActivity.this);
                    alert.setTitle("Confirm Cancellation");
                    alert.setMessage("Are you sure you want to cancel this reservation?");
                    alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // clicked ok, then user should be able to reenter to serve a seat!
                            RadioButton canceledFlight = (RadioButton) findViewById(id);
                            String[] parts = canceledFlight.getText().toString().split("\n");
                            String reservationId = parts[0].split(":")[1].trim();
                            boolean deleted = db.deleteFlightForUser(userIdArg, reservationId);
                            if(deleted){
                                Toast.makeText(ReservationsActivity.this, "Reservation successfully deleted", Toast.LENGTH_SHORT).show();
                                db.logTransaction(TransactionType.CANCELLATION, canceledFlight.getText().toString()+"user: " + getUsername());
                            }else{
                                Toast.makeText(ReservationsActivity.this, "Error! Not deleted", Toast.LENGTH_SHORT).show();
                            }
//                            Toast.makeText(ReservationsActivity.this, deleted ? "Reservation successfully deleted" : "Error! Not deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                    alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog.Builder alertNo = new AlertDialog.Builder(ReservationsActivity.this);
                            alertNo.setTitle("Failed Cancellation");
                            alertNo.setMessage("The reservations will not be cancelled!");
                            alertNo.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                            alertNo.create().show();
                        }
                    });
                    alert.setCancelable(false);
                    alert.create().show();
                }
            }
        });
    }


    // getting the userID to be able to get the reservations for that specific user
    private String getUserIdArg() {
        Intent intent = getIntent();
        if (intent != null) {
            return intent.getStringExtra(USER_KEY);
        }
        return null;
    }

    private String getUsername() {
        Intent intent = getIntent();
        return intent == null ? null : intent.getStringExtra(KEY_USER_NAME);
    }
}
