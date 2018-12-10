package edu.csumb.pdahl.project2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.csumb.pdahl.project2.Database.DatabaseHelper;
import edu.csumb.pdahl.project2.R;

public class ReserveSeatActivity extends AppCompatActivity {

    DatabaseHelper flightDB;

    Button reserveSeat;

    EditText departureCity;
    EditText arrivalCity;
    EditText numTickets;

    String deptCityInput;
    String arrCityInput;
    String numOfTicketInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seat);


        flightDB = DatabaseHelper.getInstance(getApplicationContext());

        reserveSeat = (Button) findViewById(R.id.button_reserve_ticket);
        departureCity = (EditText) findViewById(R.id.textEdit_depature);
        arrivalCity = (EditText) findViewById(R.id.textEdit_arrival);
        numTickets = (EditText) findViewById(R.id.editText_num);


        reserveSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectFlight(departureCity.getText().toString(), arrivalCity.getText().toString(), numTickets.getText().toString());
                AddData();
            }
        });


    }

    public void AddData() {
        deptCityInput = departureCity.getText().toString();
        arrCityInput = arrivalCity.getText().toString();
        numOfTicketInput = numTickets.getText().toString();

//        boolean insertData = flightDB.addFlightData(deptCityInput, arrCityInput, numOfTicketInput);
//
//        if (insertData == true) {
//            Toast.makeText(ReserveSeatActivity.this, "Flight Data Available!", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(ReserveSeatActivity.this, "No Flight data!", Toast.LENGTH_SHORT).show();
//        }
    }

    public void openSelectFlight(String departureCity, String arrivalCity, String capacity){
        Intent intent = new Intent(this, SelectFlightActivity.class);
        intent.putExtra(SelectFlightActivity.ARG_DEPARTURE_CITY, departureCity);
        intent.putExtra(SelectFlightActivity.ARG_ARRIVAL_CITY, arrivalCity);
        intent.putExtra(SelectFlightActivity.ARG_CAPACITY, capacity);
        startActivity(intent);
        finish();
    }
}
