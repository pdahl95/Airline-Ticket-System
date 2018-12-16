package edu.csumb.pdahl.project2.ui;
/**
 * Title: LoginActivity.java
 * Abstract: This is a reusable to login page, that is reused in many of the different flows.
 * It is a login page, where the input gets validated.
 * Name: Pernille Dahl
 * Date: 2018-Dec-16
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.csumb.pdahl.project2.Database.DatabaseHelper;
import edu.csumb.pdahl.project2.R;
import edu.csumb.pdahl.project2.model.User;

public class LoginActivity extends AppCompatActivity {

    public static final int LOGIN_REQUEST = 1;
    public static final String KEY_USERID = "key_user_id";
    public static final String KEY_USER_NAME = "key_user_name";
    DatabaseHelper userDB;
    Button loginBtn;
    EditText userName;
    EditText passWord;

    String userNameInput;
    String passWordInput;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button logInBtn = (Button) findViewById(R.id.logInBtn);
        userName = (EditText) findViewById(R.id.userNameId);
        passWord = (EditText) findViewById(R.id.passwordId);
        userDB = DatabaseHelper.getInstance(getApplicationContext());

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameInput = userName.getText().toString();
                passWordInput = passWord.getText().toString();
                //login - get the user from login (username)
                // use database to check is user is there or not!
                User user = userDB.getUserData(userNameInput, passWordInput);
                if(user == null) {
                    Toast.makeText(LoginActivity.this, "This user is not registered!", Toast.LENGTH_SHORT).show();
                    counter++;
                    if(counter == 2){
                        AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                        alert.setTitle("Failed Login");
                        alert.setMessage("You entered the wrong information twice!");
                        alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // clicked Confirm --> Go back to Main Menu
                                finish();
                            }
                        });
                        alert.create().show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "User exist", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra(KEY_USERID, user.getUserId());
                    intent.putExtra(KEY_USER_NAME, user.getUserName());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
