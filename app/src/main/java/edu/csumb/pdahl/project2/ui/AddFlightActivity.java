package edu.csumb.pdahl.project2.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.csumb.pdahl.project2.Database.DatabaseHelper;
import edu.csumb.pdahl.project2.R;
import edu.csumb.pdahl.project2.model.Flight;

public class AddFlightActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button addFlightManage;
    EditText manageFlightNum;
    EditText manageDeparture;
    EditText manageArrival;
    EditText manageDepartTime;
    EditText manageCapacity;
    EditText managePrice;

    String flightNum;
    String departure;
    String arrival;
    String departureTime;
    String capacity;
    String price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = DatabaseHelper.getInstance(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight);

        addFlightManage = (Button) findViewById(R.id.button);
        manageFlightNum = (EditText) findViewById(R.id.editText_addFlightNum);
        manageDeparture = (EditText) findViewById(R.id.editText_addDept);
        manageArrival = (EditText) findViewById(R.id.editText_addArr);
        manageDepartTime = (EditText) findViewById(R.id.editText_addTime);
        manageCapacity= (EditText) findViewById(R.id.editText_addCap);
        managePrice = (EditText) findViewById(R.id.editText_addPrice);

        addFlightManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData();
            }
        });


    }

    public void AddData(){
        flightNum = manageFlightNum.getText().toString();
        departure = manageDeparture.getText().toString();
        arrival = manageArrival.getText().toString();
        departureTime = manageDepartTime.getText().toString();
        capacity = manageCapacity.getText().toString();
        price = managePrice.getText().toString();
        Flight flight = new Flight(flightNum, departure, arrival, departureTime, capacity, price);

        if(flightNum.matches("") || departure.matches("") || arrival .matches("") || departureTime.matches("") ||
                capacity.matches("") || price.matches("")){
            AlertDialog.Builder alert = new AlertDialog.Builder(AddFlightActivity.this);
            alert.setTitle("Invalid Input!");
            alert.setMessage("Invalid Input entered! \n Please Confirm!");
            alert.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(AddFlightActivity.this, AirlineTicket.class);
                    startActivity(intent);
                }
            });
            alert.create().show();
        }else{
            boolean insertData = db.addFlightData(flight);

            if (insertData) {
                Toast.makeText(AddFlightActivity.this, "Data Added", Toast.LENGTH_LONG).show();
                AlertDialog.Builder alert = new AlertDialog.Builder(AddFlightActivity.this);
                alert.setTitle("Confirm Added Information");
                alert.setMessage("Is the information added correct? "
                        + "\n Flight Number: " + flightNum
                        + "\n Departure/Arrival: " + departure + " - " + arrival
                        + "\n Departure Time: " + departureTime
                        + "\n Flight capacity: " + capacity
                        + "\n Price: " + price
                );
                alert.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AddFlightActivity.this, "Flight Added!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                alert.create().show();
            } else {
                Toast.makeText(AddFlightActivity.this, "Data Not Added", Toast.LENGTH_LONG).show();
                AlertDialog.Builder alert = new AlertDialog.Builder(AddFlightActivity.this);
                alert.setTitle("Invalid Input");
                alert.setMessage("Invalid Input! Flight already exist!");
                alert.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(AddFlightActivity.this, AirlineTicket.class);
                        startActivity(intent);
                    }
                });
                alert.create().show();
            }
        }

    }
}
