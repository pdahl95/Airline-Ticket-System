package edu.csumb.pdahl.project2.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.csumb.pdahl.project2.Database.DatabaseHelper;
import edu.csumb.pdahl.project2.R;
import edu.csumb.pdahl.project2.model.TransactionType;
import edu.csumb.pdahl.project2.model.User;

public class CreateAccountActivity extends AppCompatActivity {

    DatabaseHelper userDB;

    Button createAccountBtn;
    EditText userName;
    EditText passWord;

    String userNameInput;
    String passWordInput;
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount);

        userDB = DatabaseHelper.getInstance(getApplicationContext());

        userName = (EditText) findViewById(R.id.editText_userName);
        passWord = (EditText) findViewById(R.id.editText_password);

        createAccountBtn = (Button) findViewById(R.id.button_register);


        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
//                ViewData();
            }
        });

    }

    public void validateData() {
        userNameInput = userName.getText().toString();
        passWordInput = passWord.getText().toString();
        int userNameNumberCounter = 0;
        int userNameCharCounter = 0;
        boolean usernameIsValid = false;

        int passwordNumberCounter = 0;
        int passwordCharCounter = 0;
        boolean passwordIsValid = false;

        // validate the userName input before adding it to the database
        for (int i = 0; i < userName.length(); i++) {
            if (Character.isLetter(userNameInput.charAt(i))) {
                userNameCharCounter++;
            }
            if (Character.isDigit(userNameInput.charAt(i))) {
                userNameNumberCounter++;
            }
            if (userNameNumberCounter >= 1 && userNameCharCounter >= 3) {
                usernameIsValid = true;
            }
        }

        // // validate the password input before adding it to the database
        for (int i = 0; i < passWord.length(); i++) {
            if (Character.isLetter(passWordInput.charAt(i))) {
                passwordCharCounter++;
            }
            if (Character.isDigit(passWordInput.charAt(i))) {
                passwordNumberCounter++;
            }
            if (passwordNumberCounter >= 1 && passwordCharCounter >= 3) {
                passwordIsValid = true;
            }
        }

        if (usernameIsValid == true && passwordIsValid == true) {
            AddData();
        } else {
            counter++;
            Toast.makeText(CreateAccountActivity.this, "Invalid Username or Password!", Toast.LENGTH_LONG).show();
            if(counter == 2){ // user failed twice for log in...
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Failed Login");
                alert.setMessage("Please Confirm the failed login!");
                alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // clicked Confirm --> Go back to Main Menu
                        finish();
                    }
                });
                alert.create().show();
            }
        }


    }

    public void AddData() {
        userNameInput = userName.getText().toString();
        passWordInput = passWord.getText().toString();

        boolean insertData = userDB.addUserData(userNameInput, passWordInput);

        if (insertData) {
            Toast.makeText(CreateAccountActivity.this, "Account Created Successfully!", Toast.LENGTH_LONG).show();
            userDB.logTransaction(TransactionType.NEW_ACCOUNT,
                    String.format("user %s created account.",userNameInput));
        } else {
            Toast.makeText(CreateAccountActivity.this, "Account Not Created!", Toast.LENGTH_LONG).show();
        }

        User test = userDB.getUserData(userNameInput, passWordInput);
        if(test == null) {
            Toast.makeText(CreateAccountActivity.this, "User does not exist!", Toast.LENGTH_SHORT).show();
        }
        finish();
    }




//    public void ViewData(){
//        Cursor data = userDB.showData();
//
//        if (data.getCount() == 0) {
//            display("Error", "No Data In Database.");
//            return;
//        }
//        StringBuffer buffer = new StringBuffer();
//        while (data.moveToNext()) {
//            buffer.append("ID: " + data.getString(0) + "\n");
//            buffer.append("Username: " + data.getString(1) + "\n");
//            buffer.append("Password: " + data.getString(2) + "\n");
//
//            display("All Stored Data:", buffer.toString());
//        }
//
////        data.close();
//    }

//    public void display(String title, String message){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(message);
//        builder.show();
//    }


//    void goBackToMainActivity() {
//        Intent intent = new Intent(this, AirlineTicket.class);
//        startActivity(intent);
//    }

}
