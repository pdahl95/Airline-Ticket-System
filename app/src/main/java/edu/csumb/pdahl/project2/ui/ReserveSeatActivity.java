package edu.csumb.pdahl.project2.ui;

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


    }
}
