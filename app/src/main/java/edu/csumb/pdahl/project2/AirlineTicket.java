package edu.csumb.pdahl.project2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AirlineTicket extends AppCompatActivity {

    Button createBtn;
    Button reserveBtn;
    Button cancelBtn;
    Button systemBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airline_ticket);

        createBtn = (Button) findViewById(R.id.create);
        reserveBtn = (Button) findViewById(R.id.reserve);
        cancelBtn = (Button) findViewById(R.id.cancel);
        systemBtn = (Button) findViewById(R.id.system);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateAccount();
            }
        });
        reserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReserveSeat();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCancelReservation();
            }
        });
        systemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openManageSystem();
            }
        });

    }
    public void openCreateAccount(){
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);

    }
    public void openReserveSeat(){
        Intent intent = new Intent(this, ReserveSeatActivity.class);
        startActivity(intent);
        finish();
    }
    public void openCancelReservation(){
        Intent intent = new Intent(this, CancelReservation.class);
        startActivity(intent);
    }
    public void openManageSystem(){
        Intent intent = new Intent(this, ManageSystem.class);
        startActivity(intent);
    }
}
