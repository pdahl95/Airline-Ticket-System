package edu.csumb.pdahl.project2.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.csumb.pdahl.project2.Database.DatabaseHelper;
import edu.csumb.pdahl.project2.R;
import edu.csumb.pdahl.project2.model.Log;
import edu.csumb.pdahl.project2.model.TransactionType;
import edu.csumb.pdahl.project2.model.User;

public class DisplayLog extends AppCompatActivity {

    private DatabaseHelper db;
    Button confirmBtn;
    TextView displayLogTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_log);

        confirmBtn = (Button) findViewById(R.id.button_confirm_log);
        displayLogTextView = (TextView) findViewById(R.id.textView_log_display);

        db = DatabaseHelper.getInstance(getApplicationContext());

        List<Log> logs = db.getTransactionLogs();
        if(logs.size() == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(DisplayLog.this);
            alert.setTitle("No Log Information!");
            alert.setMessage("There is no log information to display at this moment! \n Please confirm to add a new flight!");
            alert.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(DisplayLog.this, AddFlightActivity.class);
                    startActivity(intent);
                }
            });
            alert.create().show();
        }else{
            for(Log log : logs){
                displayLogTextView.append(String.format("timestamp:%s\ntype:%s\nlog:%s\n-----------------\n", log.getTimestamp(), log.getType().toString(), log.getLog()));
                displayLogTextView.setMovementMethod(new ScrollingMovementMethod());
            }
        }

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(DisplayLog.this);
                alert.setTitle("Add New Flight Confirmation");
                alert.setMessage("Do you want to add a new flight?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(DisplayLog.this, AddFlightActivity.class);
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(DisplayLog.this, AirlineTicket.class);
                        startActivity(intent);
                    }
                });
                alert.create().show();
            }
        });

    }
}
