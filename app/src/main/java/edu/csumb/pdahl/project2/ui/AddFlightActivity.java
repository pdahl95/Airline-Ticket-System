package edu.csumb.pdahl.project2.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.csumb.pdahl.project2.Database.DatabaseHelper;
import edu.csumb.pdahl.project2.R;
import edu.csumb.pdahl.project2.model.Flight;
import edu.csumb.pdahl.project2.model.TransactionType;
import edu.csumb.pdahl.project2.model.User;

public class AddFlightActivity extends AppCompatActivity {

    DatabaseHelper addFlightDB;
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
//                AddData();
            }
        });


    }

//    public void AddData(){
//        flightNum = manageFlightNum.getText().toString();
//        departure = manageDeparture.getText().toString();
//        arrival = manageArrival.getText().toString();
//        departureTime = manageDepartTime.getText().toString();
//        capacity = manageCapacity.getText().toString();
//        price = managePrice.getText().toString();
//
//        boolean insertData = addFlightDB.addFlightData(flightNum, departure, arrival, departureTime, capacity, price);
//
//        if (insertData) {
//            Toast.makeText(AddFlightActivity.this, "Data Added", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(AddFlightActivity.this, "Data Not Added", Toast.LENGTH_LONG).show();
//        }
////        finish();

//    }
}
