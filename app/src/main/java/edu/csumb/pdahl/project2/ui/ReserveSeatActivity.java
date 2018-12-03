package edu.csumb.pdahl.project2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import edu.csumb.pdahl.project2.R;

public class ReserveSeatActivity extends AppCompatActivity {

    Button reserveSeat;
    private Spinner spinner;
    private static final String[] paths = {"1", "2", "3", "4", "5", "6", "7"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seat);

        Button reserveTicketBtn = (Button) findViewById(R.id.button_reserve_ticket);
        reserveTicketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectFlight("Los Angeles", "Monterey", 5);
            }
        });


    }

    public void openSelectFlight(String departureCity, String arrivalCity, int capacity){
        Intent intent = new Intent(this, SelectFlightActivity.class);
        intent.putExtra(SelectFlightActivity.ARG_DEPARTURE_CITY, departureCity);
        intent.putExtra(SelectFlightActivity.ARG_ARRIVAL_CITY, arrivalCity);
        intent.putExtra(SelectFlightActivity.ARG_CAPACITY, capacity);
        startActivity(intent);
        finish();
    }
}
