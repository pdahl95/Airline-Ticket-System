package edu.csumb.pdahl.project2.ui;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.csumb.pdahl.project2.R;

public class CreateAccount extends AppCompatActivity {

    Button createAccountBtn;
    TextView createAccountAlertTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount);

        createAccountBtn = (Button) findViewById(R.id.register);
        createAccountAlertTextView = (TextView) findViewById(R.id.textView_successAlert);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateAccount.this);

                builder.setCancelable(true);
                builder.setTitle("Welcome Aboard!");
                builder.setMessage("Confirm New Account!");

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        createAccountAlertTextView.setVisibility(View.VISIBLE);
                    }
                });
                builder.show();
            }
        });
    }

}
