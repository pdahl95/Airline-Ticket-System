package edu.csumb.pdahl.project2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.csumb.pdahl.project2.R;

public class ManageSystem extends AppCompatActivity {

    Button manageLogin;
    EditText manageUsername;
    EditText managePassword;

    String inputManageUserName;
    String inputManagePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system);

        manageLogin = (Button) findViewById(R.id.button_man_loging);
        manageUsername = (EditText) findViewById(R.id.editText_man_username);
        managePassword = (EditText) findViewById(R.id.editText_man_password);

        manageLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputManageUserName = manageUsername.getText().toString();
                inputManagePassword = managePassword.getText().toString();
                if(inputManageUserName.equals("admin2") && inputManagePassword.equals("admin2")){
                    Toast.makeText(ManageSystem.this, "Valid Username and Password!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ManageSystem.this, DisplayLog.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(ManageSystem.this, "Invalid Username/Password! \n Please Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
