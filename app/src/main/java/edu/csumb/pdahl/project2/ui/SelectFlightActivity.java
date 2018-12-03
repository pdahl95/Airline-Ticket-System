package edu.csumb.pdahl.project2.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.csumb.pdahl.project2.R;

public class SelectFlightActivity extends AppCompatActivity {

    public static final int LOGIN_REQUEST = 1;
    public static final String ARG_DEPARTURE_CITY = "arg_departure_city";
    public static final String ARG_ARRIVAL_CITY = "arg_arrival_city";
    public static final String ARG_CAPACITY = "arg_capacity";

    private String departureCity;
    private String arrivalCity;
    private int capacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_flight);

        Intent intent = getIntent();
        departureCity = intent.getStringExtra(ARG_DEPARTURE_CITY);
        arrivalCity = intent.getStringExtra(ARG_ARRIVAL_CITY);
        capacity = intent.getIntExtra(ARG_CAPACITY, -1);

        Log.d("Angel", "departure " + departureCity + "- arrival " + arrivalCity + "- capacity " + capacity);

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
                // TODO Show confirmation pop up
                Log.d("Angel", "Login success");
            } else if (resultCode == Activity.RESULT_CANCELED) {
                finish();
            }
        }

    }

    public void openLogInActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, LOGIN_REQUEST);
    }

}
