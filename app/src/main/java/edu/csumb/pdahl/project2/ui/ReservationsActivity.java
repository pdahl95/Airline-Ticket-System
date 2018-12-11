package edu.csumb.pdahl.project2.ui;

import android.content.DialogInterface;
import android.content.Intent;
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

public class ReservationsActivity extends AppCompatActivity {

    public static final String USER_KEY = "user_key";

    private List<Flight> flights;
    private DatabaseHelper db;
    private RadioGroup displayReservationRadioGroup;
    Button cancelReservationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        final String userIdArg = getUserIdArg();

        db = DatabaseHelper.getInstance(getApplicationContext());
        flights = db.getReservationsByUserID(userIdArg);
        Log.d(this.getClassLoader().getClass().getName(), "flight size is : " + flights.size());
        displayReservationRadioGroup = findViewById(R.id.radioGroup_reservations);
        for (Flight flight : flights) {
            RadioButton radioButton = new RadioButton(this);
//            Log.d("angel", flight.getFlightId());
            radioButton.setText("Reservation Id: " + flight.getFlightId()
                    + "\nFlight Number: " + flight.getFlightNumber()
                    + "\nDeparture at "
                    + flight.getDepartureTime()
                    + "\nAvailable Seats - "
                    + flight.getCapacity()
                    + "\nPrice $"
                    + flight.getPrice()
                    + "\n");
            radioButton.setId(Integer.parseInt(flight.getFlightId()));
            displayReservationRadioGroup.addView(radioButton);
        }
        cancelReservationButton = (Button) findViewById(R.id.button_cancel_ticket);
        cancelReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = displayReservationRadioGroup.getCheckedRadioButtonId();
                if (id < 0) {
                    Toast.makeText(ReservationsActivity.this, "No flight selected!", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton canceledFlight = (RadioButton) findViewById(id);
                    String[] parts = canceledFlight.getText().toString().split("\n");
                    int selectedFlight = Integer.parseInt(parts[0].split("(?<=\\D)(?=\\d)")[1]);
                    boolean deleted = db.deleteFlightForUser(userIdArg, String.valueOf(selectedFlight));
                    Toast.makeText(ReservationsActivity.this, deleted ? "deleted" : "not deleted", Toast.LENGTH_SHORT).show();
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

    private void displayReservationsForUser(String userId){

    }
}
