package edu.csumb.pdahl.project2.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.csumb.pdahl.project2.Database.DatabaseHelper;
import edu.csumb.pdahl.project2.R;
import edu.csumb.pdahl.project2.model.TransactionType;

public class ReserveSeatActivity extends AppCompatActivity {

    DatabaseHelper flightDB;

    Button reserveSeat;

    EditText departureCity;
    EditText arrivalCity;
    EditText numTickets;

    String deptCityInput;
    String arrCityInput;
    String numOfTicketInput;

    int maxTicketCount = 0;

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
                checkForSystemRestrictions();
                if(checkForSystemRestrictions() == true){
                    openSelectFlight(departureCity.getText().toString(), arrivalCity.getText().toString(), numTickets.getText().toString());
                    AddData();
                }
            }
        });
    }

    public boolean checkForSystemRestrictions(){
        numOfTicketInput = numTickets.getText().toString();
        maxTicketCount = Integer.parseInt(numOfTicketInput);
        if(maxTicketCount > 7){
            AlertDialog.Builder alert = new AlertDialog.Builder(ReserveSeatActivity.this);
            alert.setTitle("Error!");
            alert.setMessage("Reservation was not complete! \n You requested more than 7 tickets!");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // clicked ok, then user should be able to reenter to serve a seat!
                }
            });
            alert.create().show();
            return false;
        }
        return true;
    }

    public void AddData() {
        deptCityInput = departureCity.getText().toString();
        arrCityInput = arrivalCity.getText().toString();
        numOfTicketInput = numTickets.getText().toString();
        maxTicketCount = Integer.parseInt(numOfTicketInput);

    }

    public void openSelectFlight(String departureCity, String arrivalCity, String capacity){
        Intent intent = new Intent(this, SelectFlightActivity.class);
        intent.putExtra(SelectFlightActivity.ARG_DEPARTURE_CITY, departureCity);
        intent.putExtra(SelectFlightActivity.ARG_ARRIVAL_CITY, arrivalCity);
        intent.putExtra(SelectFlightActivity.ARG_TICKET_COUNT, capacity);
        startActivity(intent);
        finish();
    }
}
