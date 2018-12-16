package edu.csumb.pdahl.project2.ui;

import android.content.Intent;
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

        for(Log log : logs){
            displayLogTextView.append(String.format("timestamp:%s\ntype:%s\nlog:%s\n-----------------\n", log.getTimestamp(), log.getType().toString(), log.getLog()));
            displayLogTextView.setMovementMethod(new ScrollingMovementMethod());
        }

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayLog.this, AddFlightActivity.class);
                startActivity(intent);
            }
        });

    }
}
