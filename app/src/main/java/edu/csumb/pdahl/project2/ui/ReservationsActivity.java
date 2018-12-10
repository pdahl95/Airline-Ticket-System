package edu.csumb.pdahl.project2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import edu.csumb.pdahl.project2.R;

public class ReservationsActivity extends AppCompatActivity {

    public static final String USER_KEY = "user_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        String userIdArg = getUserIdArg();
        if (userIdArg != null) {
            Log.d("angel", "SUCCESS");
        } else {
            Log.d("angel", "FAILURE!!!!");
        }
    }

    private String getUserIdArg() {
        Intent intent = getIntent();
        if (intent != null) {
            return intent.getStringExtra(USER_KEY);
        }

        return null;
    }
}
