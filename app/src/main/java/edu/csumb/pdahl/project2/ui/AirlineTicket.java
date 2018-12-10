package edu.csumb.pdahl.project2.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.csumb.pdahl.project2.Database.DatabaseHelper;
import edu.csumb.pdahl.project2.R;

public class AirlineTicket extends AppCompatActivity {

    private static final int REQUEST_CODE_CANCEL_RESERVATION = 1;

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

    public void openCreateAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void openReserveSeat() {
        Intent intent = new Intent(this, ReserveSeatActivity.class);
        startActivity(intent);
    }

    public void openCancelReservation() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, REQUEST_CODE_CANCEL_RESERVATION);
    }

    public void openManageSystem() {
        Intent intent = new Intent(this, ManageSystem.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CANCEL_RESERVATION) {
            // do logic depent of the result
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    String userId = data.getStringExtra(LoginActivity.KEY_USERID);
                    // start activity for display reservation
                    Intent intent = new Intent(this, ReservationsActivity.class);
                    intent.putExtra(ReservationsActivity.USER_KEY, userId);
                    startActivity(intent);
                }
            }
        }
    }
}
