package edu.csumb.pdahl.project2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import java.util.List;

import edu.csumb.pdahl.project2.Database.DatabaseHelper;
import edu.csumb.pdahl.project2.R;
import edu.csumb.pdahl.project2.model.Flight;

public class ReservationsActivity extends AppCompatActivity {

    public static final String USER_KEY = "user_key";

    private List<Flight> reservations;
    private RadioGroup displayReservation;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        String userIdArg = getUserIdArg();
        if (userIdArg != null) {
            Log.d("angel", "SUCCESS");
        } else {
            Log.d("angel", "FAILURE!!!!");
        }
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
