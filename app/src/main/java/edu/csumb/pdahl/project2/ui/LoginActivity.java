package edu.csumb.pdahl.project2.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.csumb.pdahl.project2.R;

public class LoginActivity extends AppCompatActivity {

    public static final int LOGIN_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button logInBtn = (Button) findViewById(R.id.logInBtn);
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO if logged in, send success result and finish
//                int id = getId();
//                Intent intent = new Intent();
//                intent.putExtra("arg_user_id", id);
                setResult(Activity.RESULT_OK);
                finish();
                // TODO if failure, show message first time, send failure result and finish on second try


            }
        });
    }
}
