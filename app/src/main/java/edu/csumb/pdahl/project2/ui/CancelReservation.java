package edu.csumb.pdahl.project2.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import edu.csumb.pdahl.project2.R;

public class CancelReservation extends AppCompatActivity {

    Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reservation);
    }
}
